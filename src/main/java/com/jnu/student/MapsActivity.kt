package com.jnu.student

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tencent.mapsdk.raster.model.BitmapDescriptor
import com.tencent.mapsdk.raster.model.BitmapDescriptorFactory
import com.tencent.mapsdk.raster.model.CameraPosition
import com.tencent.mapsdk.raster.model.LatLng
import com.tencent.mapsdk.raster.model.Marker
import com.tencent.mapsdk.raster.model.MarkerOptions
import com.tencent.tencentmap.mapsdk.maps.MapView
import com.tencent.tencentmap.mapsdk.maps.TencentMap
import com.tencent.tencentmap.mapsdk.maps.TencentMapOptions


class MapsActivity : AppCompatActivity() {
    private var mapView: MapView? = null
    private var tencentMap: TencentMap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // 初始化地图
        val options = TencentMapOptions()
        mapView = MapView(this, options)
        tencentMap = mapView.getMap()

        // 设置起始位置为珠海暨南大学
        val jnuLatLng = LatLng(22.248104, 113.556916)
        tencentMap.setCenter(jnuLatLng)
        tencentMap.setZoom(18) // 初始显示层级

        // 添加珠海暨南大学的图标型Marker
        val markerIcon: BitmapDescriptor =
            BitmapDescriptorFactory.fromResource(R.drawable.marker_icon)
        val marker: Marker = tencentMap.addMarker(MarkerOptions(jnuLatLng).icon(markerIcon))

        // 添加文本型标记
        tencentMap.addText(MarkerOptions(jnuLatLng, "珠海暨南大学"))

        // 给标记添加点击事件
        tencentMap.setOnMarkerClickListener(object : OnMarkerClickListener() {
            fun onMarkerClick(marker: Marker): Boolean {
                Toast.makeText(this@MapsActivity, "你点击了珠海暨南大学的标记！", Toast.LENGTH_SHORT)
                    .show()
                return true
            }
        })

        // 将地图视野移动到包含所有标记的区域
        tencentMap.moveCamera(CameraPosition.builder().target(jnuLatLng).zoom(18).build())

        // 将MapView添加到布局中
        setContentView(mapView)
    }

    override fun onDestroy() {
        super.onDestroy()
        // 在Activity销毁时销毁地图
        if (mapView != null) {
            mapView.onDestroy()
        }
    }
}

package com.amap.map3d.demo.poisearch;

import java.util.List;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.OnInfoWindowClickListener;
import com.amap.api.maps.AMap.OnMapClickListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.SupportMapFragment;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.overlay.PoiOverlay;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.poisearch.PoiSearch.OnPoiSearchListener;
import com.amap.api.services.poisearch.PoiSearch.SearchBound;
import com.amap.map3d.demo.R;
import com.amap.map3d.demo.util.ToastUtil;

/**
 * AMapV1地图中简单介绍poisearch搜索
 */
public class PoiAroundSearchActivity extends FragmentActivity implements
		OnMarkerClickListener, InfoWindowAdapter, OnItemSelectedListener,
		OnPoiSearchListener, OnMapClickListener, OnInfoWindowClickListener,
		OnClickListener {
	private AMap aMap;
	private ProgressDialog progDialog = null;// 搜索时进度条
	private Spinner selectDeep;// 选择城市列表
	private String[] itemDeep = { "酒店", "餐饮", "景区", "影院" };
	private String deepType = "";// poi搜索类型
	private PoiResult poiResult; // poi返回的结果
	private int currentPage = 0;// 当前页面，从0开始计数
	private PoiSearch.Query query;// Poi查询条件类
	private LatLonPoint lp = new LatLonPoint(39.908127, 116.375257);// 默认西单广场
	private Marker locationMarker; // 选择的点
	private PoiSearch poiSearch;
	private PoiOverlay poiOverlay;// poi图层
	private List<PoiItem> poiItems;// poi数据

	private Button nextButton;// 下一页

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.poiaroundsearch_activity);
		init();
	}

	/**
	 * 初始化AMap对象
	 */
	private void init() {
		if (aMap == null) {
			aMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();
			setUpMap();
			Button locationButton = (Button) findViewById(R.id.locationButton);
			locationButton.setOnClickListener(this);
			Button searchButton = (Button) findViewById(R.id.searchButton);
			searchButton.setOnClickListener(this);
			nextButton = (Button) findViewById(R.id.nextButton);
			nextButton.setOnClickListener(this);
			nextButton.setClickable(false);// 默认下一页按钮不可点
			locationMarker = aMap.addMarker(new MarkerOptions()
					.anchor(0.5f, 1)
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.point))
					.position(new LatLng(lp.getLatitude(), lp.getLongitude()))
					.title("西单为中心点，查其周边"));
			locationMarker.showInfoWindow();

		}
	}

	/**
	 * 设置城市选择
	 */
	private void setUpMap() {
		selectDeep = (Spinner) findViewById(R.id.spinnerdeep);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, itemDeep);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		selectDeep.setAdapter(adapter);
		selectDeep.setOnItemSelectedListener(this);// 添加spinner选择框监听事件
		aMap.setOnMarkerClickListener(this);// 添加点击marker监听事件
		aMap.setInfoWindowAdapter(this);// 添加显示infowindow监听事件

	}

	/**
	 * 注册监听
	 */
	private void registerListener() {
		aMap.setOnMapClickListener(PoiAroundSearchActivity.this);
		aMap.setOnMarkerClickListener(PoiAroundSearchActivity.this);
		aMap.setOnInfoWindowClickListener(this);
		aMap.setInfoWindowAdapter(PoiAroundSearchActivity.this);
	}

	/**
	 * 显示进度框
	 */
	private void showProgressDialog() {
		if (progDialog == null)
			progDialog = new ProgressDialog(this);
		progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progDialog.setIndeterminate(false);
		progDialog.setCancelable(false);
		progDialog.setMessage("正在搜索中");
		progDialog.show();
	}

	/**
	 * 隐藏进度框
	 */
	private void dissmissProgressDialog() {
		if (progDialog != null) {
			progDialog.dismiss();
		}
	}

	/**
	 * 开始进行poi搜索
	 */
	protected void doSearchQuery() {
		showProgressDialog();// 显示进度框
		aMap.setOnMapClickListener(null);// 进行poi搜索时清除掉地图点击事件
		currentPage = 0;
		query = new PoiSearch.Query("", deepType, "北京市");// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
		query.setPageSize(10);// 设置每页最多返回多少条poiitem
		query.setPageNum(currentPage);// 设置查第一页

		if (lp != null) {
			poiSearch = new PoiSearch(this, query);
			poiSearch.setOnPoiSearchListener(this);
			poiSearch.setBound(new SearchBound(lp, 2000, true));//
			// 设置搜索区域为以lp点为圆心，其周围2000米范围
			/*
			 * List<LatLonPoint> list = new ArrayList<LatLonPoint>();
			 * list.add(lp);
			 * list.add(AMapUtil.convertToLatLonPoint(Constants.BEIJING));
			 * poiSearch.setBound(new SearchBound(list));// 设置多边形poi搜索范围
			 */
			poiSearch.searchPOIAsyn();// 异步搜索
		}
	}

	/**
	 * 点击下一页poi搜索
	 */
	public void nextSearch() {
		if (query != null && poiSearch != null && poiResult != null) {
			if (poiResult.getPageCount() - 1 > currentPage) {
				currentPage++;

				query.setPageNum(currentPage);// 设置查后一页
				poiSearch.searchPOIAsyn();
			} else {
				ToastUtil
						.show(PoiAroundSearchActivity.this, R.string.no_result);
			}
		}
	}

	@Override
	public View getInfoContents(Marker marker) {
		return null;
	}

	@Override
	public View getInfoWindow(Marker marker) {
		return null;
	}

	/**
	 * poi没有搜索到数据，返回一些推荐城市的信息
	 */
	private void showSuggestCity(List<SuggestionCity> cities) {
		String infomation = "推荐城市\n";
		for (int i = 0; i < cities.size(); i++) {
			infomation += "城市名称:" + cities.get(i).getCityName() + "城市区号:"
					+ cities.get(i).getCityCode() + "城市编码:"
					+ cities.get(i).getAdCode() + "\n";
		}
		ToastUtil.show(PoiAroundSearchActivity.this, infomation);

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		if (parent == selectDeep) {
			deepType = itemDeep[position];
		}
		nextButton.setClickable(false);// 改变搜索条件，需重新搜索
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		if (parent == selectDeep) {
			deepType = "酒店";
		}
		nextButton.setClickable(false);// 改变搜索条件，需重新搜索
	}

	/**
	 * POI搜索回调方法
	 */
	@Override
	public void onPoiSearched(PoiResult result, int rCode) {
		dissmissProgressDialog();// 隐藏对话框
		if (rCode == 0) {
			if (result != null && result.getQuery() != null) {// 搜索poi的结果
				if (result.getQuery().equals(query)) {// 是否是同一条
					poiResult = result;
					poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
					List<SuggestionCity> suggestionCities = poiResult
							.getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
					if (poiItems != null && poiItems.size() > 0) {
						aMap.clear();// 清理之前的图标
						poiOverlay = new PoiOverlay(aMap, poiItems);
						poiOverlay.removeFromMap();
						poiOverlay.addToMap();
						poiOverlay.zoomToSpan();

						nextButton.setClickable(true);// 设置下一页可点
					} else if (suggestionCities != null
							&& suggestionCities.size() > 0) {
						showSuggestCity(suggestionCities);
					} else {
						ToastUtil.show(PoiAroundSearchActivity.this,
								R.string.no_result);
					}
				}
			} else {
				ToastUtil
						.show(PoiAroundSearchActivity.this, R.string.no_result);
			}
		} else if (rCode == 27) {
			ToastUtil
					.show(PoiAroundSearchActivity.this, R.string.error_network);
		} else if (rCode == 32) {
			ToastUtil.show(PoiAroundSearchActivity.this, R.string.error_key);
		} else {
			ToastUtil.show(PoiAroundSearchActivity.this,
					getString(R.string.error_other) + rCode);
		}
	}
	
	@Override
	public void onPoiItemSearched(PoiItem item, int rCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMapClick(LatLng latng) {
		locationMarker = aMap.addMarker(new MarkerOptions().anchor(0.5f, 1)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.point))
				.position(latng).title("点击选择为中心点"));
		locationMarker.showInfoWindow();
	}

	@Override
	public void onInfoWindowClick(Marker marker) {
		locationMarker.hideInfoWindow();
		lp = new LatLonPoint(locationMarker.getPosition().latitude,
				locationMarker.getPosition().longitude);
		locationMarker.destroy();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		/**
		 * 点击标记按钮
		 */
		case R.id.locationButton:
			aMap.clear();// 清理所有marker
			registerListener();
			break;
		/**
		 * 点击搜索按钮
		 */
		case R.id.searchButton:
			doSearchQuery();
			break;
		/**
		 * 点击下一页按钮
		 */
		case R.id.nextButton:
			nextSearch();
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onMarkerClick(Marker arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}

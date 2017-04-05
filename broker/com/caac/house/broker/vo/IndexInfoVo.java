package com.caac.house.broker.vo;

import java.util.List;

public class IndexInfoVo {

	/**滚动信息*/
	private List<ScrollInfoVo> scroll;
	
	/**推广图片*/
	private List<SpreadVo> spread;

	public List<ScrollInfoVo> getScroll() {
		return scroll;
	}

	public void setScroll(List<ScrollInfoVo> scroll) {
		this.scroll = scroll;
	}

	public List<SpreadVo> getSpread() {
		return spread;
	}

	public void setSpread(List<SpreadVo> spread) {
		this.spread = spread;
	}
}

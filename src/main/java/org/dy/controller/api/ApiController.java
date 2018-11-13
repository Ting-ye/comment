package org.dy.controller.api;

import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.dy.dto.AdDto;
import org.dy.service.AdService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private AdService adService;



	@Value("${ad.pageNum}")
	private int adPageNum;


	/**
	 * 首页 —— 广告（超值特惠）
	 */
	@RequestMapping(value = "/homead", method = RequestMethod.GET)
	public List<AdDto> homead() {
		AdDto adDto = new AdDto();
		PageHelper.startPage(1,adPageNum);
		List<AdDto> adList=adService.searchByPage(new AdDto());
		PageInfo pageInfo= new PageInfo(adList);
		return adList;
	}

}

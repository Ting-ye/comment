package org.dy.controller.api;

import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.dy.bean.Ad;
import org.dy.dto.AdDto;
import org.dy.dto.BusinessDto;
import org.dy.dto.BusinessListDto;
import org.dy.service.AdService;

import org.dy.service.BusinessService;
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

	@Resource
	private BusinessService businessService;



	@Value("${ad.pageNum}")
	private int adPageNum;


	/**
	 * 首页 —— 广告（超值特惠）
	 */
	@RequestMapping(value = "/homead", method = RequestMethod.GET)
	public List<AdDto> homead() {
		AdDto adDto = new AdDto();
		PageHelper.startPage(1,adPageNum);
		List<Ad> adList=adService.searchByPage(new AdDto());
		PageInfo pageInfo= new PageInfo(adList);
		return adService.searchByPageHelper(adList);
	}

	/**
	 * 首页 —— 推荐列表（猜你喜欢）
	 */
	@RequestMapping(value = "/homelist/{city}/{page.currentPage}", method = RequestMethod.GET)
	public BusinessListDto homelist(BusinessDto businessDto,@PathVariable("page.currentPage")int pageNum) {
		return businessService.searchByPageForApi(businessDto,pageNum);
	}

	/**
	 * 搜索结果页 - 搜索结果 - 三个参数
	 */
	@RequestMapping(value = "/search/{page.currentPage}/{city}/{category}/{keyword}", method = RequestMethod.GET)
	public BusinessListDto searchByKeyword(BusinessDto businessDto,@PathVariable("page.currentPage")int pageNum) {
		return businessService.searchByPageForApi(businessDto,pageNum);
	}

	/**
	 * 搜索结果页 - 搜索结果 - 两个参数
	 */
	@RequestMapping(value = "/search/{page.currentPage}/{city}/{category}", method = RequestMethod.GET)
	public BusinessListDto search(BusinessDto businessDto,@PathVariable("page.currentPage")int pageNum) {
		return businessService.searchByPageForApi(businessDto,pageNum);
	}

	/**
	 * 详情页 - 商户信息
	 */
	@RequestMapping(value = "/detail/info/{id}", method = RequestMethod.GET)
	public BusinessDto detail(@PathVariable("id") Long id) {
		return businessService.getById(id);
	}
}

package com.xvjia.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.xvjia.cms.domain.Article;
import com.xvjia.cms.domain.Category;
import com.xvjia.cms.domain.Channel;
import com.xvjia.cms.service.ArticleServcie;
import com.xvjia.cms.service.CategoryService;
import com.xvjia.cms.service.ChannelService;

/**
 * 
 * @ClassName: IndexController
 * @Description: cms首页
 * @author: charles
 * @date: 2019年9月19日 上午8:27:28
 */
@RequestMapping(value = { "index", "", "/" })
@Controller
public class IndexController {

	@Resource
	private ChannelService channelService;

	@Resource
	private CategoryService categoryService;
	@Resource
	private ArticleServcie articleService;

	// 进入系统首页
	@GetMapping(value = "")
	public String index(Model model, Article article, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "5") Integer pageSize) {

		// 0设置查询条件
		article.setStatus(1);// 查询审过的文章

		// 1查询出所有栏目
		List<Channel> channels = channelService.selects();
		model.addAttribute("channels", channels);
		// 2.如果栏目为null 则查询热门文章
		if (article.getChannelId() == null) {
			article.setHot(1);
			PageInfo<Article> info = articleService.selects(article, pageNum, pageSize);
			model.addAttribute("hotArticles", info.getList());
			model.addAttribute("page", info);
		}

		// 3.如果栏目不为null 则查询栏目下的分类及文章
		if (article.getChannelId() != null) {
			// 查询栏目下所有的分类
			List<Category> categorys = categoryService.selectsByChannelId(article.getChannelId());
			model.addAttribute("categorys", categorys);
			// 栏目或分类下 的文章
			PageInfo<Article> info = articleService.selects(article, pageNum, pageSize);
			model.addAttribute("articles", info.getList());
			model.addAttribute("page", info);

		}
		// 封装的查询条件
		model.addAttribute("article", article);

		// 3.最新文章.--按照文章发布日期倒序显示最近的10篇文章
		Article article2 = new Article();
		article2.setStatus(1);
		PageInfo<Article> info2 = articleService.selects(article2, 1, 10);
		model.addAttribute("lastArticles", info2.getList());

		return "index/index";

	}

}

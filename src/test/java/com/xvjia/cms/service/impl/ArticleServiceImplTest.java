package com.xvjia.cms.service.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;
import com.xvjia.cms.domain.Article;
import com.xvjia.cms.domain.Category;
import com.xvjia.cms.domain.Channel;
import com.xvjia.cms.service.ArticleServcie;
import com.xvjia.cms.service.CategoryService;
import com.xvjia.cms.service.ChannelService;
import com.xvjia.common.utils.DateUtil;
import com.xvjia.common.utils.RandomUtil;
import com.xvjia.common.utils.StreamUtil;

/**
 * @author xvjia 时间2019年9月10日
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class ArticleServiceImplTest {

	@Resource
	private ArticleServcie articleServcie;

	@Resource
	private CategoryService categoryService;

	@Autowired
	private ChannelService cService;

	@Test
	public void testInsterSelective() {

		File file = new File("F:\\桌面\\大数据系-9月-专高1-《项目开发管理+CMS系统》-月考（技能）附件");

		File[] files = file.listFiles();

		for (File file2 : files) {
			// 用工具类获取文件内容
			String content = StreamUtil.readTextFile(file2);
			System.out.println("------------------------------------");
			System.out.println(content);

//			创建一个文章对象用来存放
			Article article = new Article();

//			获取文件名
			String filename = file2.getName();

//			去掉文件后缀名
			String title = filename.substring(0, filename.lastIndexOf("."));

			System.out.println("title---" + title);

//			将文件名存到文章名里
			article.setTitle(title);

//			将文件内容存到文章内容
			article.setContent(content);

//			截取前140个字作为摘要
			String summary = content.substring(0, 140);
			article.setSummary(summary);

//			点击量呵是否热门、频道随机生产
			article.setHits(RandomUtil.random(0, 100000));
			article.setHot(RandomUtil.random(0, 1));

			int i = RandomUtil.random(1, 9);
			article.setChannelId(i);

//			根据栏目ID查询对应的分类集合
			List<Category> Categorys = categoryService.selectsByChannelId(i);

//			从集合中随机获取一个分类ID
			article.setCategoryId(Categorys.get(RandomUtil.random(0, Categorys.size() - 1)).getId());

//			文章发布日期从2019年1月1日模拟到今天

			/*
			 * Calendar calendar = Calendar.getInstance(); // 用2019-01-01 0:0:0初始化日历
			 * calendar.set(2019, 1, 1, 0, 0, 0); Date date =
			 * DateUtil.randomDate(calendar.getTime(), new Date());
			 */

//			发布时间
			article.setCreated(new Date());
//			修改时间
			article.setUpdated(new Date());

//			发布人:小黑
			article.setUserId(135);
//			已审核
			article.setStatus(1);
//			未删除
			article.setDeleted(0);

			articleServcie.insertSelective(article);

		}

	}

	@Test
	public void testSelects1() {
		List<Channel> selects = cService.selects();

		for (Channel channel : selects) {
			System.out.println(channel);
		}
	}

	/**
	 * Test method for
	 * {@link com.xvjia.cms.service.impl.ArticleServiceImpl#selects(com.xvjia.cms.domain.Article, int, int)}.
	 */
	@Test
	public void testSelects() {
		Article article = new Article();
		article.setStatus(0);
		PageInfo<Article> selects = articleServcie.selects(article, 0, 3);
		List<Article> list = selects.getList();

		for (Article article2 : list) {
			System.out.println(article2);
		}
	}

	/**
	 * Test method for
	 * {@link com.xvjia.cms.service.impl.ArticleServiceImpl#insertSelective(com.xvjia.cms.domain.Article)}.
	 */
	@Test
	public void testInsertSelective() {
		articleServcie.selects(new Article(), 1, 100);
	}

	/**
	 * Test method for
	 * {@link com.xvjia.cms.service.impl.ArticleServiceImpl#selectByPrimaryKey(java.lang.Integer)}.
	 */
	@Test
	public void testSelectByPrimaryKey() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.xvjia.cms.service.impl.ArticleServiceImpl#updateByPrimaryKeyWithBLOBs(com.xvjia.cms.domain.Article)}.
	 */
	@Test
	public void testUpdateByPrimaryKeyWithBLOBs() {
		fail("Not yet implemented");
	}

}

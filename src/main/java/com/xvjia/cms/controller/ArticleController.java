package com.xvjia.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xvjia.cms.domain.Article;
import com.xvjia.cms.domain.Comment;
import com.xvjia.cms.domain.User;
import com.xvjia.cms.service.ArticleServcie;
import com.xvjia.cms.service.CommentService;
import com.xvjia.cms.service.UserService;

/**
 * @author xvjia 时间2019年9月11日
 * 
 */
@Controller
@RequestMapping("article")
public class ArticleController {

	@Autowired
	private ArticleServcie articleServcie;

	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;

	@ResponseBody
	@GetMapping("checkPre")
	public boolean checkPre(Article article) {
		Article pre = articleServcie.selectPre(article);
		return pre != null;
	}

	@GetMapping("head")
	public String head() {
		return "my/uploading";
	}
	@ResponseBody
	@PostMapping("deleteArticle")
	public boolean deleteArticle(Article article) {
		System.out.println("----------------"+article);
		
		return articleServcie.updateByPrimaryKeySelective(article)>0;
	}

	@PostMapping("head")
	public String head(MultipartFile file, String id,HttpServletRequest request,Model model,RedirectAttributes redirectAttributes) {
//		获取原石上传那文件的名称
		String originalFilename = file.getOriginalFilename();
//		为了防止图片名称重复，使用UUID 统一处理上传的名称
		String newFielname = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));

		
		int idi = Integer.parseInt(id);
/*
  //		清空缓存
		HttpSession session = request.getSession();
		
		
//		重新登录
		User user2 = (User)session.getAttribute("user");
		user2.setPassword(Md5Util.md5Encoding(user2.getPassword()));
		System.out.println("========================================"+user2);
		User user3 = userService.login(user2);
		session.setAttribute("user", user3);		
 */
		
		File file2 = new File("d:/pic/" + newFielname);

		User user = new User();
		try {
			
			file.transferTo(file2);
			
			user.setHead_picture(newFielname);
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		user.setId(idi);

		userService.updateByPrimaryKeySelective(user);

		return "my/index";
	}

	@GetMapping("selectPre")
	public String selectkPre(Article article, Model model) {
		Article pre = articleServcie.selectPre(article);
		List<Comment> comments = commentService.selects(pre.getId());
		model.addAttribute("article", pre);
		model.addAttribute("comments", comments);
		return "my/article";
	}

	@ResponseBody
	@GetMapping("checkNext")
	public boolean checkNext(Article article) {
		Article pre = articleServcie.selectNext(article);
		return pre != null;
	}

	@GetMapping("selectNext")
	public String selectkNext(Article article, Model model) {
		Article pre = articleServcie.selectNext(article);
		List<Comment> comments = commentService.selects(pre.getId());
		model.addAttribute("article", pre);
		model.addAttribute("comments", comments);
		return "my/article";
	}

	@ResponseBody
	@PostMapping("insert")
	public boolean insert(Comment comment, String a) {

		System.out.println("----------------" + a);
		System.out.println("---------------" + comment);
		comment.setCreated(new Date());

		return commentService.insert(comment) > 0;
	}

	/**
	 * 发布文章
	 * 
	 * @param article
	 * @param file
	 * @return
	 */

	@ResponseBody
	@PostMapping("publish")
	public boolean publish(Article article, MultipartFile file) {
//		1.上传文章标题图片
		if (!file.isEmpty()) {
//			获取原石上传那文件的名称
			String originalFilename = file.getOriginalFilename();
//			为了防止图片名称重复，使用UUID 统一处理上传的名称
			String newFielname = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
//			文件上传路径
			String path = "d:/pic/";

			File file2 = new File(path + newFielname);

			try {
//				把文件写入硬盘
				file.transferTo(file2);
//				封装标题图片的地址

				article.setPicture(newFielname);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

//		2.把文章内容保存到数据库

//		默认
		article.setHot(0);// 非热门
		article.setStatus(0);// 待审核
		article.setHits(0);// 点击量0
		article.setDeleted(0);// 文章删除状态
		article.setCreated(new Date());// 文章上传时间
		article.setUpdated(new Date());// 文章修改时间

		return articleServcie.insertSelective(article) > 0;
	}

	@GetMapping("publish")
	public String publish() {
		return "my/publish";
	}

	@GetMapping("selectsByUser")
	public String selectsByUser(Model model, Article article, @RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "3") int pageSize) {

		PageInfo<Article> selects = articleServcie.selects(article, pageNum, pageSize);
		List<Article> list = selects.getList();
		model.addAttribute("articles", list);

		model.addAttribute("article", article);
		model.addAttribute("page", selects);

		return "my/articles";
	}

	@RequestMapping("selectsByAdmin")
	public String selectsByAdmin(Model model, Article article, @RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "3") int pageSize) {

		if (article.getStatus() == null) {
			article.setStatus(9);
		}

		PageInfo<Article> selects = articleServcie.selects(article, pageNum, pageSize);
		List<Article> list = selects.getList();

		for (Article article2 : list) {
			System.out.println(article2);
		}

		model.addAttribute("articles", list);
		model.addAttribute("article", article);
		model.addAttribute("page", selects);
		return "admin/articles";
	}

	@RequestMapping("selectByAdmin")
	public String select(Integer id, Model model) {
		Article article = articleServcie.selectByPrimaryKey(id);
		model.addAttribute("article", article);
		return "admin/article";

	}

	@SuppressWarnings("rawtypes")
	@RequestMapping("selectByUser")
	public String selectByUser(Integer id, Model model, @RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "10") int pageSize) {

		PageHelper.startPage(pageNum, pageSize);
		List<Comment> list2 = commentService.selects(id);

		PageInfo hitPageInfo = articleServcie.selectByHit();

		Article article = articleServcie.selectByPrimaryKey(id);
		PageInfo<Comment> info = new PageInfo<Comment>(list2);

		PageInfo pageInfo = articleServcie.selectArticleByCount();

		model.addAttribute("article", article);
		model.addAttribute("hitarticle", hitPageInfo.getList());
		model.addAttribute("countsarticle", pageInfo.getList());
		model.addAttribute("comments", info.getList());
		model.addAttribute("page", info);

		return "my/article";

	}

	@ResponseBody
	@PostMapping("update")
	public boolean update(Article article) {
		return articleServcie.updateByPrimaryKeySelective(article) > 0;
	}

}

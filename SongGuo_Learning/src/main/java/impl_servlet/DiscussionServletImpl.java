package impl_servlet;

import base_servlet.DiscussionBassServlet;
import com.alibaba.fastjson.JSON;
import pojo.DiscussionReply;
import pojo.Result;
import service.DiscussionService;
import service.impl.DiscussionServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;


@SuppressWarnings("unused")
@WebServlet("/discussion/*")
public class DiscussionServletImpl extends DiscussionBassServlet {
    private final DiscussionService discussionService = new DiscussionServiceImpl();

    public void addReplyToCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String s = req.getReader().readLine();
        DiscussionReply discussionReply = JSON.parseObject(s, DiscussionReply.class);
        try {
            discussionService.addOneReplyToCourseDiscussion(discussionReply);
            resp.getWriter().write(JSON.toJSONString(Result.success("ADD_OK")));
        } catch (Exception e) {
            resp.getWriter().write(JSON.toJSONString(Result.error("ADD_ERROR")));
            throw new RuntimeException(e);
        }
    }

    public void getCourseReplies(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int courseID = Integer.parseInt(req.getParameter("courseID"));
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        try {
            List<DiscussionReply> courseReplies = discussionService.getCourseReplies(courseID, currentPage, pageSize);
            resp.getWriter().write(JSON.toJSONString(Result.success("SELECT_OK", courseReplies)));
        } catch (Exception e) {
            resp.getWriter().write(JSON.toJSONString(Result.error("SELECT_ERROR")));
            throw new RuntimeException(e);
        }
    }

    public void deleteReply(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int courseID = Integer.parseInt(req.getParameter("courseID"));
        int replierID = Integer.parseInt(req.getParameter("replierID"));
        Timestamp replyDateTime;
        replyDateTime = new Timestamp(Long.parseLong(req.getParameter("replyDateTime")));

        try {
            discussionService.deleteOneReplyInCourse(courseID, replierID, replyDateTime);
            resp.getWriter().write(JSON.toJSONString(Result.success("DELETE_OK")));
        } catch (Exception e) {
            resp.getWriter().write(JSON.toJSONString(Result.error("DELETE_ERROR")));
            throw new RuntimeException(e);
        }
    }
}
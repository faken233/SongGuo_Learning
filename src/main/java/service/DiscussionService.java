package service;

import pojo.DiscussionReply;

import java.sql.Timestamp;
import java.util.List;

public interface DiscussionService {
    // 删除一条评论
    void deleteOneReplyInCourse(int courseID, int replierID, Timestamp replyDateTime);

    // 添加一条评论
    void addOneReplyToCourseDiscussion(DiscussionReply discussionReply);

    // 查询课程的所有评论
    List<DiscussionReply> getCourseReplies(int courseID, int currentPage, int pageSize);
}

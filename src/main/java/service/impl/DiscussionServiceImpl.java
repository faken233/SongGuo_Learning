package service.impl;

import dao.DiscussionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.DiscussionReply;
import service.DiscussionService;
import utils.mybatis.utils.MapperProxyFactory;

import java.sql.Timestamp;
import java.util.List;

public class DiscussionServiceImpl implements DiscussionService {
    private static final Logger log = LoggerFactory.getLogger(DiscussionServiceImpl.class);
    private final DiscussionMapper discussionMapper = MapperProxyFactory.getMapper(DiscussionMapper.class);

    @Override
    public void addOneReplyToCourseDiscussion(DiscussionReply discussionReply) {

        int courseID = discussionReply.getCourseID();
        int replierID = discussionReply.getReplierID();
        String replierName = discussionReply.getReplierName();
        String content = discussionReply.getContent();

        log.info("ID为{}的用户在ID为{}的课程上发布了一条回复", replierID, courseID);

        discussionMapper.addOneReplyToCourseDiscussion(replierID, courseID, replierName, content);
    }

    @Override
    public List<DiscussionReply> getCourseReplies(int courseID, int currentPage, int pageSize) {
        int offset = (currentPage - 1) * pageSize;
        return discussionMapper.getCourseReplies(courseID, offset, pageSize);
    }

    @Override
    public void deleteOneReplyInCourse(DiscussionReply discussionReply) {

        int courseID = discussionReply.getCourseID();
        int replierID = discussionReply.getReplierID();
        Timestamp replyDateTime = discussionReply.getReplyDateTime();

        log.info("ID为{}的用户删除了一条在ID为{}的课程上的评论", replierID, courseID);

        discussionMapper.deleteOneReplyInCourse(courseID, replierID, replyDateTime);
    }
}

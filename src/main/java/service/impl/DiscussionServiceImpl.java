package service.impl;

import dao.DiscussionMapper;
import pojo.DiscussionReply;
import service.DiscussionService;
import utils.mybatis.utils.MapperProxyFactory;

import java.sql.Timestamp;
import java.util.List;

public class DiscussionServiceImpl implements DiscussionService {
    private final DiscussionMapper discussionMapper = MapperProxyFactory.getMapper(DiscussionMapper.class);

    @Override
    public void addOneReplyToCourseDiscussion(DiscussionReply discussionReply) {

        int courseID = discussionReply.getCourseID();
        int replierID = discussionReply.getReplierID();
        String replierName = discussionReply.getReplierName();
        String content = discussionReply.getContent();

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

        discussionMapper.deleteOneReplyInCourse(courseID, replierID, replyDateTime);
    }
}

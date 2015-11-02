package com.yy.ent.client;

import com.yy.ent.protocol.JettyResponse;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2015/10/21
 * Time: 11:41
 * To change this template use File | Settings | File Templates.
 */
public class ClientHandler extends ChannelHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientHandler.class);

    private ReplyWaitQueue replyQueue = new ReplyWaitQueue();


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            JettyResponse resp = (JettyResponse) msg;
            Integer id = resp.getMsgId();
            ReplyFuture future = replyQueue.take(id);
            future.onReceivedReply(resp);
            LOGGER.debug("result = {}", resp.toString());
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }


}

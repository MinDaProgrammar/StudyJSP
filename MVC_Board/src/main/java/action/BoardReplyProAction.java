package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardReplyProservice;
import vo.ActionForward;
import vo.BoardDTO;

public class BoardReplyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardReplyProAction");
		ActionForward forward = null;
		BoardDTO board = new BoardDTO();
		board.setBoard_num(Integer.parseInt(request.getParameter("board_num")));
		board.setBoard_name(request.getParameter("board_name"));
		board.setBoard_pass(request.getParameter("board_pass"));
		board.setBoard_subject(request.getParameter("board_subject"));
		board.setBoard_content(request.getParameter("board_content"));
		board.setBoard_re_ref(Integer.parseInt(request.getParameter("board_re_ref")));
		board.setBoard_re_lev(Integer.parseInt(request.getParameter("board_re_lev")));
		board.setBoard_re_seq(Integer.parseInt(request.getParameter("board_re_seq")));
		System.out.println(board);

		BoardReplyProservice service = new BoardReplyProservice();
		boolean isReplySuccess = service.replyBoard(board);
		
		if(!isReplySuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script> alert('답글 등록 실패!'); history.back(); </script>");
		}else {
			forward = new ActionForward();
			forward.setPath("BoardList.bo?pageNum="+request.getParameter("pageNum"));
			forward.setRedirect(true);
		}
		
		return forward;
	}

}

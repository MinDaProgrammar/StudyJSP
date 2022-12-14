package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardModifyProService;
import vo.ActionForward;
import vo.BoardDTO;

public class BoardModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("BoardModifyProAction");
		BoardModifyProService service = new BoardModifyProService();
		BoardDTO board = new BoardDTO();
		board.setBoard_name(request.getParameter("board_name"));
		board.setBoard_num(Integer.parseInt(request.getParameter("board_num")));
		board.setBoard_subject(request.getParameter("board_subject"));
		board.setBoard_content(request.getParameter("board_content"));
		board.setBoard_pass(request.getParameter("board_pass"));
		System.out.println(board);
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		boolean isBoardWriter = service.isBoardWriter(board_num, board.getBoard_pass() );
		
		if(!isBoardWriter) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('수정 권한 없음!'); history.back() </script>");
		}else {
			boolean isModifySuccess = service.modifyBoard(board); 
			if(!isModifySuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('수정 실패!'); history.back() </script>");
			}else {
				forward = new ActionForward();
				forward.setPath("BoardDetail.bo?board_num="+board_num+"&pageNum="+request.getParameter("pageNum"));
				forward.setRedirect(true);
			}
		}

		return forward;
	}

}

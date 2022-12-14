package action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.BoardWriteProService;
import vo.ActionForward;
import vo.BoardDTO;

public class BoardWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardWriteProAction");
		ActionForward forward = null;
		
//		글쓰기 작업 실행
//		String board_name = request.getParameter("board_name");
//		System.out.println(board_name);
		
		//MultipartRequest 객체에서 파라미터 받아오기
		String uploadPath="upload";
		int filesize = 1024*1024*10;
		
		ServletContext context = request.getServletContext();
		String realPath = context.getRealPath(uploadPath);
		System.out.println(realPath);
		
		MultipartRequest multi = new MultipartRequest(request, realPath, filesize, "UTF-8", new DefaultFileRenamePolicy());
		
		BoardDTO board = new BoardDTO();
		board.setBoard_name(multi.getParameter("board_name"));
		board.setBoard_pass(multi.getParameter("board_pass"));
		board.setBoard_subject(multi.getParameter("board_subject"));
		board.setBoard_content(multi.getParameter("board_content"));
		board.setBoard_file(multi.getOriginalFileName("board_file"));
		board.setBoard_real_file(multi.getFilesystemName("board_file"));
//		System.out.println(board);
		
		//비지니스 요청 수행을 위한 service
		BoardWriteProService service = new BoardWriteProService();
		boolean isWriteSuccess = service.registBoard(board);
		if(!isWriteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('글쓰기 실패!'); history.back(); </script>");
		} else {
			forward = new ActionForward();
			forward.setPath("BoardList.bo");
			forward.setRedirect(true);
		}
		return forward;
	}

}

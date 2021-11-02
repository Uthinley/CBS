package bt.cbs.zrr.controllers;

import bt.cbs.zrr.auth.LoginDTO;
import bt.cbs.zrr.data.FileUploadDTO;
import bt.cbs.zrr.data.db.bankDeposit.BankDepositService;
import bt.cbs.zrr.global.base.BaseController;
import bt.cbs.zrr.global.common.CommonService;
import bt.cbs.zrr.global.dto.CurrentUser;
import bt.cbs.zrr.global.dto.ResponseMessage;
import bt.cbs.zrr.global.library.DateUtil;
import bt.cbs.zrr.research.paper.ResearchDTO;
import bt.cbs.zrr.research.paper.ResearchService;
import bt.cbs.zrr.research.comment.ResearchCommentService;
import bt.cbs.zrr.setup.user.UserSetupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(value = "")
public class HomeController extends BaseController {
    @Autowired
    private BankDepositService bankDepositService;
    @Autowired
    private ResearchService researchService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ResearchCommentService commentService;

    @RequestMapping(value = {"/", "home"}, method = RequestMethod.GET)
    public void index(HttpServletRequest request, HttpServletResponse response, Model model)
            throws IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LoginDTO loginDTO = (LoginDTO) auth.getPrincipal();
        CurrentUser currentUser = new CurrentUser();
        currentUser.setBranchCode(loginDTO.getBranchCode());
        currentUser.setBranchName(loginDTO.getBranchName());
        currentUser.setUserID(loginDTO.getUserId().intValue());
        currentUser.setUserName(loginDTO.getUserName());
        currentUser.setFullName(loginDTO.getUserFullName());
        currentUser.setGroupId(loginDTO.getGroupId());
        currentUser.setBankId(loginDTO.getBankId());
        currentUser.setDzongkhagId(loginDTO.getDzongkhagId());
        currentUser.setServerDate(new Date());
        request.getSession().setAttribute("currentUser", currentUser);

        if (loginDTO.isPasswordChangeYN()) {
            response.sendRedirect("changePassword");
        }else {
            response.sendRedirect("dashboard");
        }
//<<<<<<< HEAD
//        ModelAndView modelAndView = new ModelAndView();
//        model.addAttribute("applicationStatusCode", commonService.getApplicationStatusCode());
//        model.addAttribute("currentDate", DateUtil.formatDate(currentUser.getServerDate()));
//        model.addAttribute("summaryReport", researchService.getSummaryReport());
//        modelAndView.setViewName("home");
//        return modelAndView;
//=======
//        response.sendRedirect("dashboard");
    }


    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseMessage save(HttpServletRequest request, FileUploadDTO fileUploadDTO) throws Exception {
        return bankDepositService.save(fileUploadDTO);
    }

    @ResponseBody
    @RequestMapping(value="/saveReviewerComments")
    public ResponseMessage saveReviewerComments(HttpServletRequest request, Integer researchId, String rComment){
        currentUser=getCurrentUser(request);
        String statusId = "R";
        commentService.reviewerComment(researchId, rComment, statusId,currentUser);
        return researchService.saveReviewerComments(researchId, rComment, statusId);
    }

    @ResponseBody
    @RequestMapping(value = "/getAllResearchList", method= RequestMethod.GET)
    public List<ResearchDTO> getResearchList(HttpServletRequest request){
        currentUser = getCurrentUser(request);
        return researchService.geAllResearchList();
    }

    @ResponseBody
    @RequestMapping(value = "/getResearcherList", method = RequestMethod.GET)
    public List<UserSetupDTO> getResearcherList() {
        return researchService.getResearcherList();
    }

    @ResponseBody
    @RequestMapping(value = "/getReviewedResearchList", method = RequestMethod.GET)
    public List<ResearchDTO> getReviewedResearchList() {
        return researchService.getReviewedResearchList();
    }

}



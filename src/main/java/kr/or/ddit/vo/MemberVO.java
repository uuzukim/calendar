package kr.or.ddit.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = "memId") // primary키 정해주는것 
@ToString(exclude = {"memPass","memRegno1","memRegno2"})// 뭘 배제할거냐가 중요함. 
public class MemberVO implements Serializable{
	//transient 직렬화 제외 코드 . 마샬린을 제외시킨건 아니야. 이거 왜해? 정보 보안 하기 위해서 
	@NotBlank(groups ={InsertGroup.class, DeleteGroup.class}) // insertgroup으로 묶인다는것. 그럼 지금 여기에는 그룹이 2개. 인설트그룹과 디폴트 그룹.
	private String memId;
	@NotBlank(groups = {Default.class, DeleteGroup.class})
	@Size(min = 4, max = 12, groups = {Default.class, DeleteGroup.class})
	@JsonIgnore // 얘는 마샬린할때 이거 무시해 이런 뜻 그럼 비밀번호는 마샬린 안한다 
	private transient String memPass;
	@NotBlank(groups = {InsertGroup.class})
	private String memName;
	@NotBlank(groups = InsertGroup.class)
	private transient String memRegno1;
	@NotBlank(groups = InsertGroup.class)
	private transient String memRegno2;
	private LocalDateTime memBir; 
	@NotBlank
	private String memZip;
	@NotBlank
	private String memAdd1;
	@NotBlank
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	@NotBlank
	private String memHp;
	@Email
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	private LocalDate memMemorialday;
	private Integer memMileage;
	private boolean memDelete;
	
	private List<ProdVO> prodList; // has many 
	
}

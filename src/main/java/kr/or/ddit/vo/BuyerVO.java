package kr.or.ddit.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;

import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "buyerId")

public class BuyerVO {

	@NotBlank(groups = {UpdateGroup.class, DeleteGroup.class})
	private String buyerId;
	@NotBlank
	private String buyerName;
	@NotBlank
	private String buyerLgu;
	private LprodVO lprod; // has a 관계
	private String buyerBank;
	private String buyerBankno;
	private String buyerBankname;
	private String buyerZip;
	private String buyerAdd1;
	private String buyerAdd2;
	@NotBlank
	private String buyerComtel;
	@NotBlank
	private String buyerFax;
	@NotBlank
	private String buyerMail;
	private String buyerCharger;
	private String buyerTelext;
	
	//private ProdVO prod; // has a 관계 (1:1)이 거래처와는 거래를 하나씩밖에 못한다는 뜻 
	private List<ProdVO> prodList; // has many(1:N) 일대다 관계. 거래처 하나에 상품 여러개
	
	
}


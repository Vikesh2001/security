package com.security.testing.dto;

import java.math.BigDecimal;
import java.util.List;

public class FeeStatusDto {
    private Long studentId;
    private String studentName;
    private BigDecimal totalFees;
    private BigDecimal totalPaid;
    private BigDecimal balance;
    private List<FeePaymentDto> feePayments;
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public BigDecimal getTotalFees() {
		return totalFees;
	}
	public void setTotalFees(BigDecimal totalFees) {
		this.totalFees = totalFees;
	}
	public BigDecimal getTotalPaid() {
		return totalPaid;
	}
	public void setTotalPaid(BigDecimal totalPaid) {
		this.totalPaid = totalPaid;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public List<FeePaymentDto> getFeePayments() {
		return feePayments;
	}
	public void setFeePayments(List<FeePaymentDto> feePayments) {
		this.feePayments = feePayments;
	}
    
    
}

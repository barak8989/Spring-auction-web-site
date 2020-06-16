// File: AuctionUpdate.java
// AuctionUpdate class represents auction in  the system. Contains constructors, getters,
// setters, overridden equals and hashcode and also nextBidUpdate method.

package spring.model;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

//  This class represents entity in table of auctions.
@Entity
@Table(name = "auction_update")
public class AuctionUpdate {
	
	

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Size(min=2,max=30)
	@Column(name = "ProductName")
	private String productName;
	
	@Column(name = "startDate")
	/* @Temporal(TemporalType.TIMESTAMP) */
	@DateTimeFormat(pattern="yyyy/MM/dd hh:mm:ss")
	private Date startDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	@Column(name = "endDate")
	private Date endDate;
	
	
	@Column(name = "endTime")
	private String endTime;
		

	@Column(name = "category")
	private String category;
	
	@Size(min=5,max=255)
	@Column(name = "Description")
	private String description;
	
	@Column(name = "startPrice")
	private int startPrice;

	@Column(name="bid", columnDefinition="Decimal(10,2)")
	private double bid;

	@Column(name="nextBid", columnDefinition="Decimal(10,2)")
	private double nextBid;


	// Relation between two tables(users and auctions)
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "seller_id")
	private SiteUser seller;

	@Column(name = "buyer")
	private Long buyer;
	
	@Column(name = "buyerEmail")
	private String buyerEmail;
	
	@Column(name = "number_of_bids")
	private int numOfBids;


	// This method is part of constructor. Method assigns to some members default values.
	@PrePersist
	protected void onCreate() throws ParseException {
		if (startDate == null) {
			startDate =  new Date();
		}
		
		int hh = Integer.parseInt(endTime.split(":")[0]);
		int mm = Integer.parseInt(endTime.split(":")[1]);
		endDate = new Date(endDate.getYear(), endDate.getMonth(), endDate.getDate(), hh, mm);;
	
		if (bid == 0) {
			bid = startPrice;
		}
		numOfBids = 0;
		nextBid = nextBidUpdate(bid); 
	}

	// Default Constructor
	public AuctionUpdate() {
	}


	//Constuctor
	public AuctionUpdate(@Size(min = 2, max = 30) String productName, Date startDate, Date endDate,
						 String category, @Size(min = 5, max = 255) String description, int startPrice) {
		this.productName = productName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.category = category;
		this.description = description;
		this.startPrice = startPrice;
	}
	
	

	/////////////// 						GETTERS AND SETTERS										/////////////
	public double getNextBid() {
		return nextBid;
	}

	public void setNextBid(double nextBid) {
		this.nextBid = nextBidUpdate(nextBid);
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(int startPrice) {
		this.startPrice = startPrice;
	}

	public double getBid() {
		return bid;
	}

	public void setBid(double bid) {
		this.bid = bid;
	}

	public SiteUser getSeller() {
		return seller;
	}

	public void setSeller(SiteUser seller) {
		this.seller = seller;
	}

	public Long getBuyer() {
		return buyer;
	}

	public void setBuyer(Long buyer) {
		this.buyer = buyer;
	}
	
	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}
	
	public int getNumOfBids() {
		return numOfBids;
	}

	public void setNumOfBids(int numOfBids) {
		this.numOfBids = numOfBids;
	}

	/**
	 * Calculate a next bid that will kill a current bid
	 * @param bid holder of model and view
	 * @return nextBid
	 */
	public double nextBidUpdate(double bid) {
    	double nextBid = 0 ;
    	if(bid<10)
    		nextBid = bid+1;
    	else if(bid<100)
    		nextBid = bid+5;
    	else if(bid<1000)
    		nextBid = bid+10;
    	else 
    		nextBid = bid +50;
    	
    	return nextBid;
    }
	
    ////////////		OVERRIDE AND HASHCODE			//////////
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AuctionUpdate)) return false;
		AuctionUpdate that = (AuctionUpdate) o;
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}

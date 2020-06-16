// File: AuctionUpdateDao.java
// AuctionUpdateDao interface gets ability find auctions by seller, delete auction by id,
// find auctions by category sorted by endDate, and other.
package spring.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

//  This is a DAO interface for auctions in DB
public interface AuctionUpdateDao extends PagingAndSortingRepository<AuctionUpdate, Long>{
	AuctionUpdate findFirstByOrderByStartDateDesc();
	void deleteById(Long id);
	List<AuctionUpdate> findByCategoryOrderByEndDate(String category);
	List<AuctionUpdate> findBySeller(SiteUser seller);
	List<AuctionUpdate> findAllByEndDateAfter(Date date);

}

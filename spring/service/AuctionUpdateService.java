// File: AuctionUpdateService.java
// AuctionUpdateService class is service provides ability to add/update/get/remove auctions.
// Updates bid of specific auction, removes/add auction, finds all auctions and others.
package spring.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.model.AuctionUpdate;
import spring.model.AuctionUpdateDao;
import spring.model.SiteUser;

//  Service to access to auctions in DB
@Service
public class AuctionUpdateService {
    private final static int PAGESIZE = 4;


    // Access to auctions
    @Autowired
    private AuctionUpdateDao auctionUpdateDao;

    // Access to users
    @Autowired
    private UserService userService;


    /**
     * Saves auction.
     * @param auctionUpdate represents auction
     */
    public void save(AuctionUpdate  auctionUpdate) {
        auctionUpdate.setSeller(userService.getUser());
        auctionUpdateDao.save( auctionUpdate);
    }

    /**
     * Update if new bid is legal
     * @param auctionUpdate represents auction with new bid
     */
    @Transactional
    public void updateBid(AuctionUpdate auctionUpdate){
        AuctionUpdate previousAuction = auctionUpdateDao.findById(auctionUpdate.getId()).get();
        //check that new bid is bigger than previous               check that buyer isn't seller
        if((previousAuction.getBid() < auctionUpdate.getBid()) && (previousAuction.getSeller().getId() != userService.getUser().getId())){
            previousAuction.setBuyer(userService.getUser().getId());
            previousAuction.setBuyerEmail(userService.getUser().getEmail());
            previousAuction.setBid(auctionUpdate.getBid());
            previousAuction.setNumOfBids(previousAuction.getNumOfBids() + 1);
            previousAuction.setNextBid(auctionUpdate.getBid());
            auctionUpdateDao.save(previousAuction);
        }
    }

    /**
     * Returns latest by startDate(desc) auction
     * @return AuctionUpdsate auction
     */
    @Bean
    public AuctionUpdate getLatests() {
        return auctionUpdateDao.findFirstByOrderByStartDateDesc();
    }


    /**
     * Returns "Page" of auctions
     * @param pageNumber
     * @return Page of auctions
     */
    public Page<AuctionUpdate> getPage(int pageNumber) {
        PageRequest request = PageRequest.of(pageNumber-1, PAGESIZE, Sort.Direction.DESC, "endDate");
        return auctionUpdateDao.findAll(request);
    }

    /**
     * Removes auction
     * @param id is auction key
     */
    public void delete(Long id){
        auctionUpdateDao.deleteById(id);
    }


    /**
     * Finds auction by id(key)
     * @param id
     */
    public AuctionUpdate get(Long id) {
        return auctionUpdateDao.findById(id).get();

    }


    /**
     * Finds almost(see note) all auctions in specific category sorted by endTime.
     * Note: If current user published auction, remove auction from list
     * @param category
     * @param userId
     * @return updated modelAndView
     */
    public List<AuctionUpdate> getAuctionsByCategorySortedByTime(String category, Long userId){
        List<AuctionUpdate> auctions = auctionUpdateDao.findByCategoryOrderByEndDate(category);
        for(AuctionUpdate auction: auctions){
            //removes all auctions belong to current user
            if(auction.getSeller().getId() == userId)
                auctions.remove(auction);
        }
        return  auctions;
    }


    /**
     * Finds all auction of specific user
     * @param user
     * @return list of auctions
     */
    public List<AuctionUpdate> getItemsBySeller(SiteUser user)
    {
        return auctionUpdateDao.findBySeller(user);
    }

}

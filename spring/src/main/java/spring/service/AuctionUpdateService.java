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


@Service
public class AuctionUpdateService {
    private final static int PAGESIZE = 4;

    @Autowired
    private AuctionUpdateDao auctionUpdateDao;

    @Autowired
    private UserService userService;

    public void save(AuctionUpdate  auctionUpdate) {
        auctionUpdate.setSeller(userService.getUser());
        auctionUpdateDao.save( auctionUpdate);
    }

    //Update if new bid is legal
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
            return;
        }
        //here if bid isn't legal maybe send to user what a is wrong?
    }

    @Bean// do we need this been here?
    public AuctionUpdate getLatests() {
        return auctionUpdateDao.findFirstByOrderByStartDateDesc();
    }

    public Page<AuctionUpdate> getPage(int pageNumber) {
        PageRequest request = PageRequest.of(pageNumber-1, PAGESIZE, Sort.Direction.DESC, "endDate");
        return auctionUpdateDao.findAll(request);
    }

    public void delete(Long id){
        auctionUpdateDao.deleteById(id);
    }

    public AuctionUpdate get(Long id) {
        return auctionUpdateDao.findById(id).get();

    }

    public List<AuctionUpdate> getAuctionsByCategorySortedByTime(String category, Long userId){
        List<AuctionUpdate> auctions = auctionUpdateDao.findByCategoryOrderByEndDate(category);
        for(AuctionUpdate auction: auctions){
            //removes all auctions belong to current user
            if(auction.getSeller().getId() == userId)
                auctions.remove(auction);
        }
        return  auctions;
    }

    public List<AuctionUpdate> getItemsBySeller(SiteUser user)
    {
        return auctionUpdateDao.findBySeller(user);
    }

}

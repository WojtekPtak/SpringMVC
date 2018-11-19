Student: Wojciech Ptak


Task description:

1. Add new entity to the application - UserAccount, it should store the amount of prepaid money user has in the system,
which should be used during booking procedure.
DONE
The data is visible in management of users - as a separate list/table - I wasn't sure how to manage this second list


Add methods for refilling the account to the BookingFacade class.
- Not done but I'm not sure - such class should be created ?

Add DAO and service objects for new entity.
DONE

Add ticketPrice field to Event entity.
- not done - we have basePrice already?

2. Update ticket booking methods to check and withdraw money from user account according to the ticketPrice for particular event.

3. Configure appropriate PlatformTransactionManager implementation in Spring application context.
IT WAS NOT ALREADY DONE?

4. Make ticket booking methods transactional using Spring declarative transactions management (either xml or annotation based config).
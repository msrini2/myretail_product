This file explains principles used to define various DAO (Data Access Object) approaches used in family of Offers API/ Ecoupon API services
===========================================================================================================================================

It is not mandated by any standard or any other requirement to have our dao specific interfaces and classes organized in a way we do.
However due to complexity of code and richness of features we have in fact fairly strict standards on our own.
This file attempts to define these standards and principles and reasoning behind them.
The goal of this document and overall organization of dao frameworks is to help streamline development process, keep it easily
understood and maintainable. All approaches used are also trying to adhere to popular existing coding practices and frameworks.

What we use:
-----------

Hibernate:
By default we use Hibernate ORM (Object Relational Model) which assumes mainly these things:
- POJO classes are defined to match actual DB tables
- Persistence API annotations are used to map DB tables and columns to POJO classes and fields
- Hibernate and Spring specific classes are used to manipulate DB objects: HibernateSession, etc

While Hibernate is generally very useful and can be performance-tuned to greatest degree, the complexity of our features have gone beyond
simple management and at this time we are considering to stop any new Hibernate development and instead switch to plain JDBC
and Spring-Managed JDBC (JdbcTemplate) frameworks. This said we are still going to keep using any Hibernate specific code as it is already
developed, tested and just works. Those poor-performing tasks will however be converted to use plain JDBC/JdbcTemplate as needs arise.
It is possible that at some point in time we will completely switch from Hibernate to JDBC and possibly other frameworks as we see a match.

JDBC:
JDBC - is a framework of choice for many. Reason - simplicity and availability. It however needs to be mentioned that while plain JDBC is generally
well-performing (provided we write good SQL queries), there is a need to have more code implemented to achieve similar tasks as opposed to
using Hibernate framework.

JdbcTemplate:
JdbcTemplate by Spring - is a very nice light framework that provides simplified way to have a combination of plain JDBC and ORM.
At this point we are not yet into JdbcTemplate, but we will be evaluating potential to switch or use in parallel.
Here is few points that need to be understood about JdbcTemplate:
- While it offers reasonable ORM features, these features require developers to write their own code to actually support ORM
- It is still using JDBC in plain manner, only providing some additional utilities-like facilities to arrive at higher level code and also allows
  for more type control. In fact if using ORM by JdbcTemplate, for every POJO there will be several more classes each providing specific way to
  work with that POJO (assign values to fields using JDBC), which in turn makes the whole code base to be at least about twice as big compared to
  Hibernate.
- While this framework is ORM in nature, it still required to create own SQL statements. This gives more control, but requires to write more code.
- Allows named parameters to be used (plain JDBC does not as you know and is using '?' placeholders for every value to be passed to DB)


Direct JDBC versus Hibernate.JDBC:
We also have flavors that use both: JDBC via directly obtaining Connection and JDBC via getting Connection from Hibernate Session.
Hibernate allows to use and share the same pooled connections as regular Hibernate code. This also implicitly uses hard-configured timeout of 2-3 seconds.
Direct JDBC allows access to Connection without pooling features and without having to used timeout.
Direct JDBC also allows access to other databases where we only reading data from using only few queries (DB2 and TI RepoDB)

How we use it:
-------------

There are few specific packages that we historically maintain. There will likely be some changes and some package added/moved/deleted.
This is more or less the full packaging structure as it exists today:

- com.sears.offers.dao - the basic package where we have most interfaces that are specific to Hibernate
- com.sears.offers.dao.impl - the basic implementation package to have most implementations specific to Hibernate
- com.sears.offers.ecoupon.dao - interfaces related to ecoupon, specific to Hibernate
- com.sears.offers.ecoupon.dao.impl - implementations related to ecoupon, specific to Hibernate
- com.sears.offers.batch.dao - interfaces related to batch processing, specific to Hibernate, however one interface+class specific to JDBC needs to be moved
- com.sears.offers.batch.dao.impl - implementations related to batch processing, specific to Hibernate
- com.sears.offers.promo.dao - interface+implementation related to promoanalyzer processing, specific to JDBC
- com.sears.offers.model, com.sears.offers.model.ecoupon, com.sears.offers.model.promo - POJO with ORM annotations specific to Hibernate
- com.sears.offers.dao.sql - classes specific to plain SQL and manipulation with JDBC (can work with Hibernate too)
- com.sears.offers.dao.txmanager - newly developed framework specific to Hibernate, offers session-aware and transaction automation code


Note: following packages while named after DAO do not have anything specific to do with any DB related code (still related to Data Access Object notation),
or are considered Deprecated and need to be renamed/moved as appropriate:
- com.sears.offers.dao.bo - used to keep some helpful classes
- com.sears.offers.dao.cache - used to keep cache related code
- com.sears.offers.dao.service - used to keep code related to higher level DAO logic (not specific to DB)
- com.sears.offers.ecoupon.dao.service - has one off interface that needs to be moved to proper package
- com.sears.offers.dao.util - has one off class that needs to find another home, specific to Hibernate
- com.sears.offers.repo - one off class related to Offer Repository SQL, plain JDBC (may also need to find another home)

It also should be mentioned that newly developed session-aware framework (for Hibernate) is partially placed in the existing DaoBase code
that has Hibernate specific older methods, but allowing different way of coding (no direct session or transaction management is needed). 


How we should change to use it:
------------------------------

As clear from above there is a little bit of good and messy configurations as it is specific to package naming and code placement.
This should be reorganized to simplify understanding, support and for better classification of DAO/DB related code in general.

Here is the proposed configuration for all the code mentioned above:

- any code not directly related to DB (like Cache, Service, helpful Business Objects, etc) needs to loose the DAO/DB/JDBC package denomination
  and be moved to proper packages
- any code directly related to DAO/DB/JDBC that is not in package named after dao/jdbc needs to move to proper packages too
- any code related to Hibernate should live under *..dao.orm and *..dao.orm.impl, interfaces under .dao.orm, implementations under corresponding .dao.orm.impl
  later on these classes may be completely removed in favor of jdbc/jdbc template
- any code related to session-aware features should have its own home (probably *.dao.orm.tx or *.dao.orm.session or *.dao.orm.txmanager)
  and do not mix regular and session-aware Hibernate code. It may also need to have interface and implementation specific packages
- any code related to JDBC manipulations like utilities and other helpful JDBC stuff should move to com.sears.offers.dao.jdbc.util
- since there is only one Hibernate specific class+method under com.sears.offers.dao.util we may move it to basic DaoBase class or something
- any code related to helping plain SQL use/generation should stay in com.sears.offers.dao.sql
- any code related to interface+implementations using JDBC rather than Hibernate should be in *..dao.jdbc, *..dao.jdbc.impl
- any SQL+JDBC specific code that uses Hibernate Session should be converted to use plain JDBC
- any code obtaining JDBC connection that does not share pool should live separately (home to be defined, maybe dao.jdbc.util)
- should create connection+transaction-aware framework for JDBC similar to Hibernate session-aware framework (home to be defined, maybe .dao.jdbc.tx)
- JDBC Template by Spring has not been chosen yet, but if and when it is - should live in .dao.jdbctmpl.*

It should also be mentioned that the session-aware framework is defined as utilizing any package ending in .dao.impl (for Hibernate code),
therefore proper package naming is essential to support this feature AS IS (otherwise changes and customizations are necessary).

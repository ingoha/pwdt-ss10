/*
Created		1/26/2007
Modified		2/23/2007
Project		
Model			
Company		
Author		
Version		
Database		PostgreSQL 8.1 
*/



/* Drop Referential Integrity Triggers */





/* Drop User-Defined Triggers */



/* Drop Domains */



/* Drop Procedures */



/* Drop Views */



/* Drop Indexes */



/* Drop Tables */
Drop table "certifiable_expression" Restrict;
Drop table "action_type" Restrict;
Drop table "action" Restrict;
Drop table "expression" Restrict;
Drop table "property" Restrict;
Drop table "system_preference" Restrict;
Drop table "project" Restrict;
Drop table "certifiable_expression_organization" Restrict;
Drop table "user_preference" Restrict;
Drop table "resource" Restrict;
Drop table "attribute_change" Restrict;
Drop table "data_type" Restrict;
Drop table "row_change" Restrict;
Drop table "auditable_entity" Restrict;
Drop table "access_type" Restrict;
Drop table "user_role" Restrict;
Drop table "privilege" Restrict;
Drop table "securable_object_type" Restrict;
Drop table "securable_object" Restrict;
Drop table "role" Restrict;
Drop table "phone_number_type" Restrict;
Drop table "email_address_type" Restrict;
Drop table "phone_number" Restrict;
Drop table "email_address" Restrict;
Drop table "party" Restrict;
Drop table "organization" Restrict;
Drop table "guser" Restrict;



/* Create Domains */



/* Create Sequences */
create sequence id_seq;




/* Create Tables */


Create table "guser"
(
	"userid" Integer NOT NULL UNIQUE,
	"username" Text NOT NULL UNIQUE,
	"password" Text NOT NULL,
	"jobtitle" Text,
	"firstname" Text NOT NULL,
	"lastname" Text NOT NULL,
	"company" Text,
 primary key ("userid")
) Without Oids;


Create table "organization"
(
	"organizationid" Integer NOT NULL UNIQUE,
	"name" Text NOT NULL UNIQUE,
	"contactname" Text,
 primary key ("organizationid")
) Without Oids;


Create table "party"
(
	"partyid" Integer NOT NULL UNIQUE,
	"website" Text,
	"address1" Text,
	"address2" Text,
	"address3" Text,
	"city" Text,
	"state" Text,
	"zip" Text,
	"country" Text,
 primary key ("partyid")
) Without Oids;


Create table "email_address"
(
	"emailaddressid" Integer NOT NULL UNIQUE,
	"emailaddresstypeid" Integer NOT NULL,
	"partyid" Integer NOT NULL,
	"value" Text,
 primary key ("emailaddressid")
) Without Oids;


Create table "phone_number"
(
	"phonenumberid" Integer NOT NULL UNIQUE,
	"phonenumbertypeid" Integer NOT NULL,
	"partyid" Integer NOT NULL,
	"value" Text NOT NULL,
 primary key ("phonenumberid")
) Without Oids;


Create table "email_address_type"
(
	"emailaddresstypeid" Integer NOT NULL UNIQUE,
	"name" Text NOT NULL UNIQUE,
	"descr" Char(255),
 primary key ("emailaddresstypeid")
) Without Oids;


Create table "phone_number_type"
(
	"phonenumbertypeid" Integer NOT NULL UNIQUE,
	"name" Text NOT NULL UNIQUE,
	"descr" Char(255),
 primary key ("phonenumbertypeid")
) Without Oids;


Create table "role"
(
	"roleid" Integer NOT NULL UNIQUE,
	"name" Text NOT NULL UNIQUE,
	"descr" Char(255),
 primary key ("roleid")
) Without Oids;


Create table "securable_object"
(
	"securableobjectid" Integer NOT NULL UNIQUE,
	"securableobjecttypeid" Integer NOT NULL,
	"name" Text NOT NULL UNIQUE,
 primary key ("securableobjectid")
) Without Oids;


Create table "securable_object_type"
(
	"securableobjecttypeid" Integer NOT NULL UNIQUE,
	"name" Text NOT NULL UNIQUE,
	"descr" Char(20),
 primary key ("securableobjecttypeid")
) Without Oids;


Create table "privilege"
(
	"securableobjectid" Integer NOT NULL,
	"accesstypeid" Integer NOT NULL,
	"roleid" Integer NOT NULL,
 primary key ("securableobjectid","accesstypeid","roleid")
) Without Oids;


Create table "user_role"
(
	"roleid" Integer NOT NULL,
	"userid" Integer NOT NULL,
 primary key ("roleid","userid")
) Without Oids;


Create table "access_type"
(
	"accesstypeid" Integer NOT NULL UNIQUE,
	"name" Text NOT NULL UNIQUE,
	"descr" Char(255),
 primary key ("accesstypeid")
) Without Oids;


Create table "auditable_entity"
(
	"auditableentityid" Integer NOT NULL UNIQUE,
	"name" Text NOT NULL UNIQUE,
	"descr" Char(255),
 primary key ("auditableentityid")
) Without Oids;


Create table "row_change"
(
	"rowchangeid" Integer NOT NULL UNIQUE,
	"userid" Integer NOT NULL,
	"auditableentityid" Integer NOT NULL,
	"changedate" Date NOT NULL,
	"rowprimaryid" Integer NOT NULL,
 primary key ("rowchangeid")
) Without Oids;


Create table "data_type"
(
	"datatypeid" Integer NOT NULL UNIQUE,
	"name" Text NOT NULL UNIQUE,
	"descr" Char(255),
 primary key ("datatypeid")
) Without Oids;


Create table "attribute_change"
(
	"attributechangeid" Integer NOT NULL UNIQUE,
	"rowchangeid" Integer NOT NULL,
	"datatypeid" Integer NOT NULL,
	"affectedattribute" Char(20),
	"pretext" Text,
	"preinteger" Integer,
	"predate" Date,
	"prechar" Char(255),
	"posttext" Text,
	"postinteger" Integer,
	"postdate" Date,
	"postchar" Char(255),
 primary key ("attributechangeid")
) Without Oids;


Create table "resource"
(
	"resourceid" Integer NOT NULL UNIQUE,
	"url" Text,
	"title" Text NOT NULL UNIQUE,
	"body" Text NOT NULL,
	"sort" Integer NOT NULL,
 primary key ("resourceid")
) Without Oids;


Create table "user_preference"
(
	"userpreferenceid" Integer NOT NULL UNIQUE,
	"userid" Integer NOT NULL,
	"name" Text NOT NULL,
	"value" Text,
	"descr" Char(255),
 primary key ("userpreferenceid")
) Without Oids;


Create table "certifiable_expression_organization"
(
	"organizationid" Integer NOT NULL,
	"certifiableexpressionid" Integer NOT NULL,
 primary key ("organizationid","certifiableexpressionid")
) Without Oids;


Create table "project"
(
	"projectid" Integer NOT NULL UNIQUE,
	"uniqueid" Char(255) NOT NULL,
	"name" Text NOT NULL UNIQUE,
	"createdby" Text NOT NULL,
	"descr" Text,
	"repository" Text NOT NULL,
	"templatetype" Text NOT NULL,
	"drugclass" Text NOT NULL,
	"status" Text NOT NULL,
 primary key ("projectid")
) Without Oids;


Create table "system_preference"
(
	"systempreferenceid" Integer NOT NULL UNIQUE,
	"name" Text NOT NULL UNIQUE,
	"value" Text,
	"descr" Char(255),
 primary key ("systempreferenceid")
) Without Oids;


Create table "property"
(
	"propertyid" Integer NOT NULL UNIQUE,
	"dont_know_yet" Char(20),
	"expressionid" Integer NOT NULL,
 primary key ("propertyid")
) Without Oids;


Create table "expression"
(
	"expressionid" Integer NOT NULL UNIQUE,
	"projectid" Integer NOT NULL,
 primary key ("expressionid")
) Without Oids;


Create table "action"
(
	"actionid" Integer NOT NULL UNIQUE,
	"userid" Integer NOT NULL,
	"actiononid" Integer,
	"actiontypeid" Integer NOT NULL,
	"actiondate" Date NOT NULL,
 primary key ("actionid")
) Without Oids;


Create table "action_type"
(
	"actiontypeid" Integer NOT NULL UNIQUE,
	"name" Text NOT NULL UNIQUE,
	"descr" Char(255),
 primary key ("actiontypeid")
) Without Oids;


Create table "certifiable_expression"
(
	"certifiableexpressionid" Integer NOT NULL UNIQUE,
	"name" Text NOT NULL,
	"descr" Char(255),
 primary key ("certifiableexpressionid")
) Without Oids;



/* Create Tab 'Others' for Selected Tables */


/* Create Alternate Keys */
Alter Table "user_preference" add Constraint "unique_userid_name" UNIQUE ("name");



/* Create Indexes */



/* Create Foreign Keys */

Alter table "row_change" add  foreign key ("userid") references "guser" ("userid") on update restrict on delete cascade;

Alter table "user_preference" add  foreign key ("userid") references "guser" ("userid") on update restrict on delete cascade;

Alter table "action" add  foreign key ("userid") references "guser" ("userid") on update restrict on delete cascade;

Alter table "user_role" add  foreign key ("userid") references "guser" ("userid") on update restrict on delete cascade;

Alter table "certifiable_expression_organization" add  foreign key ("organizationid") references "organization" ("organizationid") on update restrict on delete restrict;

Alter table "phone_number" add  foreign key ("partyid") references "party" ("partyid") on update restrict on delete cascade;

Alter table "email_address" add  foreign key ("partyid") references "party" ("partyid") on update restrict on delete cascade;

Alter table "organization" add  foreign key ("organizationid") references "party" ("partyid") on update restrict on delete cascade;

Alter table "guser" add  foreign key ("userid") references "party" ("partyid") on update restrict on delete cascade;

Alter table "email_address" add  foreign key ("emailaddresstypeid") references "email_address_type" ("emailaddresstypeid") on update restrict on delete restrict;

Alter table "phone_number" add  foreign key ("phonenumbertypeid") references "phone_number_type" ("phonenumbertypeid") on update restrict on delete restrict;

Alter table "user_role" add  foreign key ("roleid") references "role" ("roleid") on update restrict on delete restrict;

Alter table "privilege" add  foreign key ("roleid") references "role" ("roleid") on update restrict on delete restrict;

Alter table "privilege" add  foreign key ("securableobjectid") references "securable_object" ("securableobjectid") on update restrict on delete restrict;

Alter table "action" add  foreign key ("actiononid") references "securable_object" ("securableobjectid") on update restrict on delete restrict;

Alter table "securable_object" add  foreign key ("securableobjecttypeid") references "securable_object_type" ("securableobjecttypeid") on update restrict on delete restrict;

Alter table "privilege" add  foreign key ("accesstypeid") references "access_type" ("accesstypeid") on update restrict on delete restrict;

Alter table "row_change" add  foreign key ("auditableentityid") references "auditable_entity" ("auditableentityid") on update restrict on delete restrict;

Alter table "attribute_change" add  foreign key ("rowchangeid") references "row_change" ("rowchangeid") on update restrict on delete restrict;

Alter table "attribute_change" add  foreign key ("datatypeid") references "data_type" ("datatypeid") on update restrict on delete restrict;

Alter table "expression" add  foreign key ("projectid") references "project" ("projectid") on update restrict on delete restrict;

Alter table "property" add  foreign key ("expressionid") references "expression" ("expressionid") on update restrict on delete restrict;

Alter table "action" add  foreign key ("actiontypeid") references "action_type" ("actiontypeid") on update restrict on delete restrict;

Alter table "certifiable_expression_organization" add  foreign key ("certifiableexpressionid") references "certifiable_expression" ("certifiableexpressionid") on update restrict on delete restrict;



/* Create Procedures */



/* Create Views */



/* Create Referential Integrity Triggers */





/* Create User-Defined Triggers */



/* Create Roles */



/* Add Roles To Roles */



/* Create Role Permissions */
/* Role permissions on tables */

/* Role permissions on views */

/* Role permissions on procedures */


-- certifiable expressions

INSERT INTO certifiable_expression
(certifiableexpressionid, name, descr)
VALUES
(1, 'Drug SPL Expressions', '');

INSERT INTO certifiable_expression
(certifiableexpressionid, name, descr)
VALUES
(2, 'Clinical Guideline Expressions', '');

INSERT INTO certifiable_expression
(certifiableexpressionid, name, descr)
VALUES
(3, 'Clinical Trial Expressions', '');

INSERT INTO certifiable_expression
(certifiableexpressionid, name, descr)
VALUES
(4, 'POC Decision Support Expressions', '');

-- populate email address type
INSERT INTO email_address_type
(emailaddresstypeid, name, descr)
VALUES
(1,'FIRST', 'primary email address');

INSERT INTO email_address_type
(emailaddresstypeid, name, descr)
VALUES
(2,'SECOND', 'secondary email address');

INSERT INTO email_address_type
(emailaddresstypeid, name, descr)
VALUES
(3, 'THIRD', 'tertiary email address');

INSERT INTO email_address_type
(emailaddresstypeid, name, descr)
VALUES
(4, 'ORGANIZATION_CONTACT', 'organization contact email address');

-- populate phone number type
INSERT INTO phone_number_type
(phonenumbertypeid, name, descr)
VALUES
(1,'HOME', 'home phone number');

INSERT INTO phone_number_type
(phonenumbertypeid, name, descr)
VALUES
(2, 'WORK', 'work phone number');

INSERT INTO phone_number_type
(phonenumbertypeid, name, descr)
VALUES
(3,'MOBILE', 'mobile phone number');

INSERT INTO phone_number_type
(phonenumbertypeid, name, descr)
VALUES
(4,'ORGANIZATION_CONTACT', 'organization contact phone number');

-- populate role
INSERT INTO role
(roleid, name, descr)
VALUES
(1, 'system administrator', 'The System Administrator');

INSERT INTO role
(roleid, name, descr)
VALUES
(2, 'demonstrator', '');

INSERT INTO role
(roleid, name, descr)
VALUES
(3, 'expression author', '');

INSERT INTO role
(roleid, name, descr)
VALUES
(4, 'expression test case author', '');

INSERT INTO role
(roleid, name, descr)
VALUES
(5, 'cie author', '');

INSERT INTO role
(roleid, name, descr)
VALUES
(6, 'cie test case author', '');

INSERT INTO role
(roleid, name, descr)
VALUES
(7, 'internal approver', '');

INSERT INTO role
(roleid, name, descr)
VALUES
(8,'external certifier', '');

INSERT INTO role
(roleid, name, descr)
VALUES
(9,'project manager', '');

-- add a sysadmin
INSERT INTO party
(partyid) 
VALUES 
(nextval('id_seq')); -- increment the partyid

INSERT INTO guser
(userid, username, password, jobtitle, firstname, lastname)
VALUES
(currval('id_seq'), 'admin', 'q6Q3/x/TBWQac', 'System Administrator', 'sys', 'admin');

INSERT INTO user_role
(userid, roleid)
VALUES
(currval('id_seq'), (SELECT roleid FROM role WHERE name = 'system administrator'));


-- add resources
INSERT INTO resource
(resourceid, url, title, body, sort)
VALUES
(nextval('id_seq'), 'http://www.ahrq.gov/clinic/cpgsix.htm', 'U.S. Department of Health & Human Services (HHS)
Agency for Healthcare Research & Quality (AHRQ)', 'This web site is a link to the National Guideline Clearinghouse (NGC), a public resource for evidence-based clinical practice guidelines. NGC is an initiative of the Agency for Healthcare Research and Quality (AHRQ), U.S. Department of Health and Human Services. NGC was originally created by AHRQ in partnership with the American Medical Association and the American Association of Health Plans (now America\'s Health Insurance Plans [AHIP]).', 0);

INSERT INTO resource
(resourceid, url, title, body, sort)
VALUES
(nextval('id_seq'), 'http://www.cs.amedd.army.mil/qmo/pguide.htm', 'VA / DoD Clinical Practice Guidelines', 'This web site contains clinical practice guidelines used by the Veteran\'s Administration (VA) and Department of Defense (DoD). These guidelines are issued through the U.S. Army MEDCOM Quality Management Office.', 1);

INSERT INTO resource
(resourceid, url, title, body, sort)
VALUES
(nextval('id_seq'), 'http://www.nlm.nih.gov/medlineplus/medicines.html', 'National Library of Medicine (NLM) DailyMed', 'The DailyMed provides high quality information about marketed drugs. This information includes FDA approved labels (package inserts). This Web site provides health information providers and the public with a standard, comprehensive, up-to-date, look-up and download resource of medication content and labeling as found in medication package inserts.
Other information about prescription drugs may also be available. NLM regularly processes data files uploaded from FDA\'s system and provides and maintains this Web site for the public to use in accessing the information. Additional information about medicines is available on NLM\'s MedlinePlus Web site.', 2);

INSERT INTO resource
(resourceid, url, title, body, sort)
VALUES
(nextval('id_seq'), 'http://www.infermed.com/arezzo/', 'Arezzo Guideline Development System', 'The AREZZO clinical decision support software enables the design, creation, and execution of clinical guidelines and patient care protocols that guide medical professionals with advice tailored for each patient individually. AREZZO guides the user through the collection of that data which will assist in making decisions about the optimum clinical actions that should be taken in complex patient care situations.', 3);

 
 commit;






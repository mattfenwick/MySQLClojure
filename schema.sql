use mysqlclojure;


create table mediumtable (
 id                 int primary key auto_increment,
 decimalfield1      decimal(10,2),
 indexedstringfield varchar(10),
   index isf (indexedstringfield(4)),
 indexfield1        int,
 indexfield2        int,
   index if1if2 (indexfield1, indexfield2)
);


create table largetable (
 id             int primary key auto_increment,
 intfield1      int,
 indexedintfield int,
   index iif (indexedintfield),
 stringfield    varchar(50),
 foreignkeyfield int,
   foreign key (foreignkeyfield) references mediumtable (id),
 indexfield1    int,
 indexfield2    int,
 indexfield3    int,
   index if1if2if3 (indexfield1, indexfield2, indexfield3),
 notnullint     int not null,
 notnullstring  varchar(20) not null
);
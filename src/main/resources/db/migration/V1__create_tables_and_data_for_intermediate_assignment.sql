CREATE TABLE article (
  id             SERIAL       NOT NULL,
  title           VARCHAR(100) NOT NULL,
  published_date DATE,
  content        TEXT,
  PRIMARY KEY (id)
);

-- Random copy-paste from http://technews.techfact.org/
INSERT INTO article (title, published_date, content)
VALUES ('Nike present back to the future style self lacing shoes', '2016-03-28', 'Nike HyperAdapt 1.0 is achieving the tech imagined by back to the future,  “ the self lacing shoes” are here! And ready for consumer use …');

INSERT INTO article (title, published_date, content)
VALUES ('#Japan plans to make #bitcoin a legal #currency !', '2016-03-29', 'Japanese news outlet Nikkei reported that the country’s governors are planning to propose changes to the legal system which would define virtual currency such as Bitcoin, Dogecoin and Litecoin as official tender.');

INSERT INTO article (title, published_date, content)
VALUES ('#Facebook new #like / #emotions - FB rolls out #FacebookReactions globally', '2016-03-30', 'If you was one of those complaining about the inability to leave other reactions instead of the like, now you can, Facebook have rolled out Facebook reactions globally, it initially tested the feature in some countries, and starting today the feature have been rolled out globally.');

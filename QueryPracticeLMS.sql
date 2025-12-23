use login;
show tables;

select * from login;

select * from master order by bid desc;

select count(bid) as tot_emp from master;
select * from leavemaster;

select * from leavemaster where lev_id != 'null';

select * from master m inner join leavemaster l on m.bid = l.bid where lev_id != 'null';

select * from master m inner join leavemaster l on m.bid = l.bid where lev_remark = 'Sanctioned';
select * from master m inner join leavemaster l on m.bid = l.bid where lev_remark = 'Non-Sanctioned';


-- leave on specific date
select * from master m inner join leavemaster l on m.bid = l.bid where '2025-12-07' between lev_start_date and lev_end_date;

SELECT m .bid, m.name, m.desi, l.lev_start_date, l.lev_end_date, l.lev_days, l.lev_remark 
							   FROM master m inner join leavemaster l
							   on m.bid = l.bid where
							    '2025-12-07' BETWEEN lev_start_date AND lev_end_date;
 
-- Query to Check Current Active Leave--                 
SELECT m.name, l.*
FROM master m
JOIN leavemaster l ON m.bid = l.bid
WHERE CURRENT_DATE BETWEEN l.lev_start_date AND l.lev_end_date; 
           
-- whether any leave exists between 8–12 Dec 2025.            
SELECT *
FROM leavemaster
WHERE bid = 506  -- employee/bid
  AND lev_start_date <= '2025-12-12'
  AND lev_end_date >= '2025-12-08';

-- ⭐ Bonus: Total Leave Days Per Employee

SELECT m.bid, m.name, SUM(l.lev_days) AS total_leave_days
FROM master m
JOIN leavemaster l ON m.bid = l.bid
GROUP BY m.bid, m.name;

				



SELECT *
FROM a
         INNER JOIN b ON a.id = b.id
         LEFT JOIN c ON a.id = c.id AND b.id = c.id
WHERE a.some_column = 'some_value'
  AND b.some_column = 'some_value'
  AND (c.some_column = 'some_value' OR c.id IS NULL)



SELECT *
FROM a
         INNER JOIN b ON a.id = b.id
         INNER JOIN c ON a.id = c.id
UNION ALL
SELECT *
FROM a
         INNER JOIN b ON a.id = b.id
         LEFT JOIN c ON a.id = c.id
WHERE c.id IS NULL;


-- 作为高级程序员，优化下面的SQL语句:
select * from a,b where a.id = b.id and a.condition_a in ('1','2') and b.condition_b in ('3','4')
union all
select * from a,b,c where a.id = b.id and a.id = c.id and a.condition_a in ('1','2') and b.condition_b in ('3','4') and c.condition_c in ('6','7')


SELECT *
FROM a
         INNER JOIN b
                    ON a.id = b.id
         LEFT JOIN c
                   ON a.id = c.id
WHERE a.condition_a in ('1', '2')
  AND b.condition_b in ('3', '4')
  AND (c.condition_c in ('6', '7') OR c.condition_c IS NULL)

-- 详细解释以下SQL语句的作用
SELECT *
FROM a,
     b
WHERE a.id = b.id
  AND a.condition_a IN ('1', '2')
  AND b.condition_b IN ('3', '4')
  AND EXISTS(
        SELECT 1
        FROM c
        WHERE a.id = c.id
          AND c.condition_c IN ('6', '7')
    );
SELECT
    article_id, 
    name, 
    price, 
    `condition`, 
    place, 
    description, 
    seller_id
FROM 
    articles 
ORDER BY article_timestamp DESC 
LIMIT 1
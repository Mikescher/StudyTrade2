SELECT 
    articles.article_id, articles.name, articles.price, articles.`condition`, articles.place, articles.description,
    users.user_id, users.forename, users.lastname, users.nickname, users.city, users.university, users.studydirection, users.mail, users.activated
FROM articles 
LEFT JOIN users 
    ON articles.seller_id = users.user_id 
WHERE articles.name LIKE ?
LIMIT 25
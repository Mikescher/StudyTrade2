USE studytrade;
SELECT 
	users.forename, users.lastname, users.nickname, users.mail
FROM users 
WHERE     
	users.user_id = ?
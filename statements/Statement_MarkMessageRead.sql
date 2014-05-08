UPDATE 
    messages 
SET 
    message_read = 1 
WHERE 
    message_id = ?
LIMIT 1
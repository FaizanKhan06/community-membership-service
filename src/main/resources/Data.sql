-- Insert sample data into the community_membership_entity table

INSERT INTO community_membership_entity (
    community_id, 
    email, 
    amount, 
    is_loan_taken, 
    is_accepted, 
    is_loan_defaulter
)
VALUES 
    (1, 'b@b.com', 0.00, FALSE, TRUE, FALSE),
    (1, 'c@c.com', 0.00, FALSE, TRUE, FALSE),
    (1, 'd@d.com', 0.00, FALSE, TRUE, FALSE);
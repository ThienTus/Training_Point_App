import { Fragment } from "react";
import { Alert, Container } from "react-bootstrap";
import './Styles.css'

const Footer = () => {
    return (
        <Container className="Footer">
            <Alert variant="success" ><strong>Hoạt động ngoại khóa OU</strong> &copy; <strong>Trường Đại học Mở Tp.HCM 2024</strong></Alert>
            
        </Container>
    );
}

export default Footer;
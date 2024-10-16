import React, { useEffect, useState } from "react";
import {
  Container,
  Row,
  Col,
  Button,
  Table,
  Dropdown,
} from "react-bootstrap";
import {
  Link,
  useNavigate,
} from "react-router-dom";
import "./Styles.css";
import APIs, { endpoints } from "../../configs/APIs";
import {
  format,
  startOfWeek,
  endOfWeek,
  startOfMonth,
  endOfMonth
} from "date-fns";
import { pageSize } from "../../configs/configs";
import LoadMore from "./LoadMore";


const Home = () => {
  const [activities, setActivities] = useState([]);
  const [filteredActivities, setFilteredActivities] = useState([]);
  const [selectedFaculty, setSelectedFaculty] = useState("default");
  const [selectedArticle, setSelectedArticle] = useState("default");
  const [selectedTime, setSelectedTime] = useState("default");
  const [faculty, setFaculty] = useState([]);
  const [article, setArticle] = useState([]);
  const [page, setPage] = useState(1);
  const [loadedFull, setLoadedFull] = useState(false);
  const nav = useNavigate();

  const load = async (page = 1) => {
    let params = new URLSearchParams();
    let currentDate = new Date();
    currentDate = `${currentDate.getFullYear()}/${currentDate.getMonth() + 1
      }/${currentDate.getDate()}`;
    if (currentDate) {
      params.set("currentDate", currentDate);
    }
    if (page) {
      params.set("page", page);
    }
    let res = await APIs.get(`${endpoints["activity-list"]}?${params}`);
    const newActivities = res.data || [];
    setActivities([...activities, ...newActivities]);
    setFilteredActivities([...filteredActivities, ...newActivities]);
    updateTotalPages(newActivities.length < pageSize);
  };

  const updateTotalPages = (noMorePages) => {
    if (noMorePages) setLoadedFull(true);
  }

  const loadFilterInfo = async () => {
    let fac = await APIs.get(endpoints["faculty-list"]);
    let art = await APIs.get(endpoints["article-list"]);
    setFaculty(fac.data);
    setArticle(art.data);
  }

  const loadMore = () => {
    setPage(page + 1);
  }

  const FilteredActivities = () => {
    let updatedActivities = activities;

    if (selectedFaculty !== "default") {
      updatedActivities = updatedActivities.filter(
        (a) => a.faculty === selectedFaculty
      );
    }

    if (selectedArticle !== "default") {
      updatedActivities = updatedActivities.filter(
        (a) => a.article === selectedArticle
      );
    }

    if (selectedTime != "default") {
      const now = new Date();
      if (selectedTime === "day") {
        const current = Date.parse(now);
        updatedActivities = updatedActivities.filter((a) => {
          return current > a.startDateTime && current < a.endDateTime;
        });
      } else if (selectedTime === "week") {
        const weekStart = Date.parse(startOfWeek(now));
        const weekEnd = Date.parse(endOfWeek(now));

        updatedActivities = updatedActivities.filter((a) => {
          return a.startDateTime >= weekStart && a.endDateTime <= weekEnd;
        });
      } else if (selectedTime === "month") {
        const monthStart = Date.parse(startOfMonth(now));
        const monthEnd = Date.parse(endOfMonth(now));

        updatedActivities = updatedActivities.filter((a) => {
          return a.startDateTime >= monthStart && a.endDateTime < monthEnd;
        });
      }
    }

    setFilteredActivities(updatedActivities);
  };

  useEffect(() => {
    FilteredActivities();
  }, [selectedFaculty, selectedArticle, selectedTime]);

  useEffect(() => {
    loadFilterInfo();
  }, []);

  useEffect(() => {
    load(page);
  }, [page]);

  return (
    <>
      <Container className="home-container">
        <Row className="justify-content-center text-center">
          <Col md={8}>
            <h1 className="mt-5">
              Chào Mừng Đến Với Hệ Thống Quản Lý Điểm Rèn Luyện
            </h1>
            <p className="lead mt-3">
              Đây là hệ thống quản lý điểm rèn luyện cho sinh viên. Bạn có thể
              theo dõi và cập nhật các hoạt động và điểm rèn luyện của mình tại
              đây.
            </p>
            <div className="m-4">
              <Link to="/activity">
                <Button variant="primary" className="me-2">
                  Xem Hoạt Động
                </Button>
              </Link>
              <Link to="/report-missing">
                <Button variant="secondary">Báo Thiếu</Button>
              </Link>
            </div>
          </Col>
        </Row>
        <Dropdown className="d-inline mx-2" onSelect={(k) => setSelectedFaculty(k)}>
          <Dropdown.Toggle variant="success">Khoa</Dropdown.Toggle>
          <Dropdown.Menu>
            <Dropdown.Item eventKey="default">Tất cả</Dropdown.Item>
            {faculty.map((f) => (
              <Dropdown.Item key={f.id} eventKey={f.name}>
                {f.name}
              </Dropdown.Item>
            ))}
          </Dropdown.Menu>
        </Dropdown>

        <Dropdown className="d-inline mx-2" onSelect={(k) => setSelectedArticle(k)}>
          <Dropdown.Toggle variant="success">Điều</Dropdown.Toggle>
          <Dropdown.Menu>
            <Dropdown.Item eventKey="default">Tất cả</Dropdown.Item>
            {article.map((a) => (
              <Dropdown.Item key={a.id} eventKey={a.name}>
                {a.name}
              </Dropdown.Item>
            ))}
          </Dropdown.Menu>
        </Dropdown>

        <Dropdown className="d-inline mx-2" onSelect={(k) => setSelectedTime(k)}>
          <Dropdown.Toggle variant="success">Thời Gian</Dropdown.Toggle>
          <Dropdown.Menu>
            <Dropdown.Item eventKey="default">Tất cả</Dropdown.Item>
            <Dropdown.Item eventKey="day">Ngày hiện tại</Dropdown.Item>
            <Dropdown.Item eventKey="week">Tuần hiện tại</Dropdown.Item>
            <Dropdown.Item eventKey="month">Tháng hiện tại</Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>

        <Table responsive="sm" striped bordered hover className="p-3a m-3">
          <thead>
            <tr>
              <th>#</th>
              <th>Tên Hoạt Động</th>
              <th>Địa Điểm</th>
              <th>Điều</th>
              <th>Đối tượng</th>
              <th>Khoa</th>
              <th>Ngày giờ bắt đầu</th>
              <th>Ngày giờ kết thúc</th>
            </tr>
          </thead>
          <tbody>
            {filteredActivities.map((a, index) => (
              <tr
                key={a.id}
                onClick={() => nav(`/activity/${a.id}`)}
                style={{ cursor: "pointer" }}
              >
                <td>{index + 1}</td>
                <td>{a.name}</td>
                <td>{a.location}</td>
                <td>{a.article}</td>
                <td>{a.participant}</td>
                <td>{a.faculty}</td>
                <td>{format(a.startDateTime, "dd-MM-yyyy HH:mm")}</td>
                <td>{format(a.endDateTime, "dd-MM-yyyy HH:mm")}</td>
              </tr>
            ))}
          </tbody>
        </Table>
        {!loadedFull && <LoadMore objName="hoạt động" handleLoadMore={loadMore} />}
      </Container>
    </>
  );
};

export default Home;

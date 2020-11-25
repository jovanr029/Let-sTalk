import React from "react";
import { Table, Form, Button, ButtonGroup } from "react-bootstrap";
import LetsTalkAxios from "../../apis/LetsTalkAxios";

class Users extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      users: [],
      search: { username: "", firstName: "", lastName: "" },
      pageNum: 0,
      totalPages: 1
    };
  }

  componentDidMount() {
    this.getUsers();
  }

  getUsers(page = null) {
    let config = { params: {} };

    if(this.state.search.username !== ""){
      config.params.username = this.state.search.username
    }
    if(this.state.search.firstName !== ""){
      config.params.firstName = this.state.search.firstName;
    }
    if(this.state.search.lastName !== ""){
      config.params.lastName = this.state.search.lastName;
    }

    if (page != null) {
      config.params.pageNum = page;
    } else {
      config.params.pageNum = this.state.pageNum;
    }

    console.log(config)

    LetsTalkAxios.get("/users", config)
      .then((res) => {
        this.setState({ users: res.data, totalPages: res.headers["total-pages"] });
        console.log(this.state);
        console.log(res.headers)
      })
      .catch((error) => {
        console.log('could not get users')
      });
  }

  goToUser(userId) {
    this.props.history.push("/users/" + userId);
  }

  searchValueInputChange(event) {
    let control = event.target;

    let name = control.id;
    let value = control.value;

    let search = this.state.search;
    search[name] = value;

    this.setState({ search: search });
  }

  doSearch(){
    this.setState({ totalPages: 1, pageNum: 0 });
    this.getUsers(0);
  }

  changePage(direction){
    let page = this.state.pageNum + direction;
    this.getUsers(page);
    this.setState({ pageNum: page });
  }

  renderData() {
    return this.state.users.map((user, index) => {
      return (
        <tr key={user.id}>
          <td onClick={() => this.goToUser(user.id)}>{user.username}</td>
          <td>{user.email}</td>
          <td>{user.firstName}</td>
          <td>{user.lastName}</td>
          <td>{user.dateOfBirth}</td>
        </tr>
      );
    });
  }

  render() {
    return (
      <div>
        <h1>Users</h1>
        <Form>
          <Form.Group>
            <Form.Label>Username:</Form.Label>
            <Form.Control
              id="username"
              type="text"
              as="input"
              placeholder="Serch by username: "
              onChange={(e) => this.searchValueInputChange(e)}
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>First name:</Form.Label>
            <Form.Control
              id="firstName"
              type="text"
              as="input"
              placeholder="Search by first name: "
              onChange={(e) => this.searchValueInputChange(e)}
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Last name:</Form.Label>
            <Form.Control
              id="lastName"
              type="text"
              as="input"
              placeholder="search by last name: "
              onChange={(e) => this.searchValueInputChange(e)}
            ></Form.Control>
          </Form.Group>
          <Button onClick={() => this.doSearch()}>Search</Button>
          </Form>
        <Table>
          <thead>
            <tr>
              <th>User Name</th>
              <th>E Mail</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Date of birth</th>
            </tr>
          </thead>
          <tbody>{this.renderData()}</tbody>
        </Table>
        <ButtonGroup style={{ marginTop: 25 }}>
          <Button
            disabled={this.state.pageNum == 0}
            onClick={() => this.changePage(-1)}
          >
            Previous
          </Button>
          <Button
            disabled={this.state.pageNum + 1 == this.state.totalPages}
            onClick={() => this.changePage(1)}
          >
            Next
          </Button>
        </ButtonGroup>
      </div>
    );
  }
}
export default Users;

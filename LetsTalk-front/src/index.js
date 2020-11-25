import React from "react";
import { Container, Navbar, Nav, Button } from "react-bootstrap";
import ReactDOM from "react-dom";
import {
  HashRouter as Router,
  Link,
  Route,
  Switch,
  Redirect,
} from "react-router-dom";
import Login from "./components/authentication/Login";
import Registration from "./components/authentication/Registration";
import Home from "./components/Home";
import NotFound from "./components/NotFound";
import AddPost from "./components/posts/AddPost";
import EditPost from "./components/posts/EditPost";
import Post from "./components/posts/Post";
import PostProfile from "./components/posts/PostProfile";
import Posts from "./components/posts/Posts";
import EditProfile from "./components/users/EditProfile";
import MyProfile from "./components/users/MyProfile";
import User from "./components/users/User";
import Users from "./components/users/Users";
import { logout } from "./services/auth";

class App extends React.Component {
  render() {
    let token = window.localStorage.getItem("token");
    if (token) {
      return (
        <div>
          <Router>
            <Navbar bg="dark" variant="blue">
              <Navbar.Brand as={Link} to="/">
                Home
              </Navbar.Brand>
              <Nav className="mr-auto">
                <Nav.Link as={Link} to="/users">
                  Users
                </Nav.Link>
              </Nav>
              <Nav className="mr-auto">
                <Nav.Link as={Link} to="/posts">
                  Posts
                </Nav.Link>
              </Nav>
              <Nav className="mr-auto">
                <Nav.Link as={Link} to="/profile">
                  MyProfile
                </Nav.Link>
              </Nav>
              <Button
                onClick={() => {
                  logout();
                }}
                variant="outline-info"
              >
                Log Out
              </Button>
            </Navbar>
            <Container style={{ marginTop: 25 }}>
              <Switch>
                <Route exact path="/" component={Home} />
                <Route exact path="/users" component={Users} />
                <Route exact path="/users/:id" component={User} />
                <Route exact path="/profile" component={MyProfile} />
                <Route exact path="/profile/edit/" component={EditProfile} />
                <Route exact path="/posts" component={Posts} />
                <Route exact path="/posts/:id" component={Post} />
                <Route exact path="/post/add" component={AddPost} />
                <Route exact path="/post/edit/:id" component={EditPost} />
                <Route exact path="/postprofile" component={PostProfile} />
                <Route exact path="/login">
                  <Redirect to="/"></Redirect>
                </Route>
                <Route component={NotFound} />
              </Switch>
            </Container>
          </Router>
        </div>
      );
    } else {
      return (
        <Router>
          <Container>
            <Switch>
              <Route exact path="/login" component={Login} />
              <Route exact path="/registration" component={Registration} />
              <Route path="/">
                <Redirect to="/login"></Redirect>
              </Route>
            </Switch>
          </Container>
        </Router>
      );
    }
  }
}
ReactDOM.render(<App />, document.querySelector("#root"));

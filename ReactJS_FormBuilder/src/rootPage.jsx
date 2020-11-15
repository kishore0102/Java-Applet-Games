import React, { Component } from "react";
import "bootstrap/dist/css/bootstrap.css";
import "./styles/rootPage.css";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { Navbar, Nav } from "react-bootstrap";
import SavedFormMainPage from "./SavedForm/MainPage";
import BuildFormMainPage from "./BuildForm/MainPage";
import FormSubmissionsMainPage from "./FormSubmissions/MainPage";

class RootPage extends Component {
  render() {
    return (
      <React.Fragment>
        <Router>
          <Navbar className="navbar navbar-dark bg-dark">
            <Navbar.Collapse id="basic-navbar-nav">
              <Nav className="mr-auto">
                <Nav.Link href="/buildForm">Build New Form</Nav.Link>
                <span>&nbsp;&nbsp;&nbsp;</span>
                <Nav.Link href="/savedForm">Saved Form</Nav.Link>
                <span>&nbsp;&nbsp;&nbsp;</span>
                <Nav.Link href="/submissions">Submissions</Nav.Link>
              </Nav>
            </Navbar.Collapse>
            <Navbar.Brand href="/">Form Builder</Navbar.Brand>
          </Navbar>
          <br />
          <Switch>
            <Route exact path="/">
              <h5 className="ml-5">Welcome to Deadshot's form builder</h5>
              <h6 className="ml-5">
                Please navigate to your desired page from the top navigation bar
              </h6>
            </Route>
            <Route path="/savedForm">
              <SavedFormMainPage
                renderOutputJson={
                  JSON.parse(localStorage.getItem("renderOutputJson") === null)
                    ? []
                    : JSON.parse(localStorage.getItem("renderOutputJson"))
                }
              />
            </Route>
            <Route path="/buildForm">
              <BuildFormMainPage />
            </Route>
            <Route path="/submissions">
              <FormSubmissionsMainPage />
            </Route>
          </Switch>
        </Router>
      </React.Fragment>
    );
  }
}

export default RootPage;

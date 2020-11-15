import React, { Component } from "react";
import FormCard from "./FormCard";

class MainPage extends Component {
  state = {
    renderOutputJson: this.props.renderOutputJson,
    renderFormOutput: {},
    savedFormSelectedId: null,
  };

  handleSavedFormSelect = (event) => {
    let tempSavedFormSelectedId = event.currentTarget.id;
    this.setState({ savedFormSelectedId: tempSavedFormSelectedId });
  };

  handleSubmitForm = (event) => {
    event.preventDefault();

    var dataStr =
      "data:text/json;charset=utf-8," +
      encodeURIComponent(JSON.stringify(this.state.renderFormOutput));
    var downloadAnchorNode = document.createElement("a");
    downloadAnchorNode.setAttribute("href", dataStr);
    downloadAnchorNode.setAttribute("download", "form.txt");
    downloadAnchorNode.click();
    downloadAnchorNode.remove();

    this.setState({ renderFormOutput: {} });
    this.setState({ savedFormSelectedId: null });
  };

  handleFormChange = (event) => {
    let tempRenderFormOutput = this.state.renderFormOutput;
    tempRenderFormOutput["Form Name"] = this.state.savedFormSelectedId;
    tempRenderFormOutput[event.currentTarget.id.split("_*_")[1]] =
      event.currentTarget.value;
    this.setState({ renderFormOutput: tempRenderFormOutput });
  };

  render() {
    return (
      <React.Fragment>
        <div className="row justify-content-center">
          <div className="col-sm-2">
            <div className="card box-shadow-hover pointer">
              <div className="card-body scrollbar" id="style-2">
                <div className="list-group">
                  {this.state.renderOutputJson.map((input) => {
                    return (
                      <React.Fragment key={input.formDetails.formName}>
                        <a
                          href="# "
                          id={input.formDetails.formName}
                          className="list-group-item list-group-item-action"
                          onClick={this.handleSavedFormSelect}
                        >
                          {input.formDetails.formName}
                        </a>
                        <br />
                      </React.Fragment>
                    );
                  })}
                </div>
              </div>
            </div>
          </div>

          <div className="col-sm-8">
            <div className="card box-shadow-hover pointer">
              <div className="card-body scrollbar" id="style-2">
                <div className="list-group">
                  {this.state.renderOutputJson.map((oneForm) => {
                    if (
                      oneForm.formDetails.formName ===
                      this.state.savedFormSelectedId
                    ) {
                      return (
                        <React.Fragment key={oneForm.formDetails.formName}>
                          <form
                            id={oneForm.formDetails.formName}
                            className="form-group"
                            onSubmit={this.handleSubmitForm}
                          >
                            <div
                              key={oneForm.formDetails.formName + "_header"}
                              id={oneForm.formDetails.formName + "_header"}
                              className="list-group-item"
                            >
                              <div className="row justify-content-center">
                                <div className="col-sm-12">
                                  <label>
                                    <b>Form Name&nbsp;:&nbsp;&nbsp;&nbsp;</b>
                                    {oneForm.formDetails.formName}
                                  </label>
                                </div>
                              </div>
                            </div>
                            <br />
                            <FormCard
                              key={oneForm.formDetails.formName + "_render"}
                              oneForm={oneForm}
                              handleFormChange={this.handleFormChange}
                            />
                            <br />
                            <button
                              type="submit"
                              name={oneForm.formDetails.formName}
                              className="btn btn-danger"
                              onClick={this.handleSubmitForm}
                            >
                              Submit
                            </button>
                          </form>
                        </React.Fragment>
                      );
                    } else {
                      return (
                        <React.Fragment
                          key={oneForm.formDetails.formName}
                        ></React.Fragment>
                      );
                    }
                  })}
                </div>
              </div>
            </div>
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default MainPage;

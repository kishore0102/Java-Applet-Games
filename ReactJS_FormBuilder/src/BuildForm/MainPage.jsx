import React, { Component } from "react";
import FormTypes from "./FormTypes";
import FormRender from "./FormRender";
import ProperitiesRender from "./ProperitiesRender";

class MainPage extends Component {
  state = {
    randomNumber: 0,
    renderInput: { formDetails: { formName: "" }, formContents: [] },
    renderInputSelected: null,
  };

  handleFormNameChange = (event) => {
    let tempRenderInput = this.state.renderInput;
    tempRenderInput.formDetails[event.currentTarget.name] =
      event.currentTarget.value;
    this.setState({ renderInput: tempRenderInput });
  };

  handleFormTypes = (event) => {
    // console.log("form types clicked", event.currentTarget);
    let tempRenderInput = this.state.renderInput;
    if (event.currentTarget.id === "text") {
      tempRenderInput.formContents.push({
        type: "text",
        inputId: this.state.randomNumber + "_text",
        properities: {
          labelText: "Label",
          placeholderText: "",
          minimumLength: "0",
          maximumLength: "255",
          required: true,
        },
      });

      this.setState({ renderInput: tempRenderInput });
      this.setState({ renderInputSelected: this.state.randomNumber + "_text" });
    }

    let tempRandomNumber = this.state.randomNumber;
    tempRandomNumber++;
    this.setState({ randomNumber: tempRandomNumber });
  };

  handleRenderInputDelete = (event) => {
    // console.log("delete renderInput", event.currentTarget.id);
    let tempRenderInput = this.state.renderInput;
    tempRenderInput.formContents = tempRenderInput.formContents.filter(
      (input) => input.inputId !== event.currentTarget.id
    );
    this.setState({ renderInput: tempRenderInput });
  };

  handleRenderInputSelect = (event) => {
    // console.log("properities displayed", event.currentTarget);
    let tempRenderInputSelected = this.state.renderInputSelected;

    if (event.currentTarget.id !== null || event.currentTarget.id !== "") {
      tempRenderInputSelected = event.currentTarget.id;
    } else {
      tempRenderInputSelected = null;
    }

    this.setState({ renderInputSelected: tempRenderInputSelected });
  };

  handleRenderInputProperities_text = (event) => {
    let idImpacted = event.currentTarget.id.split("_*_")[0];
    let tempRenderInput = this.state.renderInput;
    for (let i = 0; i < tempRenderInput.formContents.length; i++) {
      if (tempRenderInput.formContents[i].inputId === idImpacted) {
        tempRenderInput.formContents[i].properities[event.currentTarget.name] =
          event.currentTarget.value;
        break;
      }
    }
    this.setState({ renderInput: tempRenderInput });
  };

  handleRenderInputProperities_boolean = (event) => {
    let idImpacted = event.currentTarget.id.split("_*_")[0];
    let details = event.currentTarget.id.split("_*_")[1];
    let changeName = details.split("_")[0];
    let changeValue = details.split("_")[1] === "yes" ? true : false;

    let tempRenderInput = this.state.renderInput;
    for (let i = 0; i < tempRenderInput.formContents.length; i++) {
      if (tempRenderInput.formContents[i].inputId === idImpacted) {
        tempRenderInput.formContents[i].properities[changeName] = changeValue;
        break;
      }
    }
    this.setState({ renderInput: tempRenderInput });
  };

  handleSaveForm = (event) => {
    document.getElementById("errorFormSave").innerHTML = "";
    if (
      this.state.renderInput.formDetails.formName === null ||
      this.state.renderInput.formDetails.formName === ""
    ) {
      document.getElementById("errorFormSave").innerHTML = "Empty Form name!";
      return false;
    }
    let renderOutputJson = JSON.parse(localStorage.getItem("renderOutputJson"));

    if (renderOutputJson != null) {
      let lsRenderOutputJson = JSON.parse(
        localStorage.getItem("renderOutputJson")
      );
      console.log("lsRenderOutputJson", lsRenderOutputJson);
      lsRenderOutputJson.push(this.state.renderInput);
      localStorage.setItem(
        "renderOutputJson",
        JSON.stringify(lsRenderOutputJson)
      );
    } else {
      let tempArray = [];
      tempArray.push(this.state.renderInput);
      localStorage.setItem("renderOutputJson", JSON.stringify(tempArray));
    }
    console.log("renderOutputJson", localStorage.getItem("renderOutputJson"));
    this.setState({
      renderInput: { formDetails: { formName: "" }, formContents: [] },
    });
  };

  render() {
    return (
      <React.Fragment>
        <div className="row">
          <div className="col-sm-2">
            <div className="card box-shadow-hover pointer">
              <div className="card-body scrollbar" id="style-2">
                <FormTypes handleFormTypes={this.handleFormTypes} />
              </div>
            </div>
          </div>

          <div className="col-sm-5">
            <div className="card box-shadow-hover pointer">
              <div className="card-body scrollbar" id="style-2">
                <div className="renderInputList">
                  <div className="list-group">
                    <FormRender
                      renderInput={this.state.renderInput}
                      handleRenderInputSelect={this.handleRenderInputSelect}
                      handleFormNameChange={this.handleFormNameChange}
                    />
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div className="col-sm-3">
            <div className="card box-shadow-hover pointer">
              <div className="card-body scrollbar" id="style-2">
                <div className="list-group">
                  <ProperitiesRender
                    renderInput={this.state.renderInput}
                    renderInputSelected={this.state.renderInputSelected}
                    handleRenderInputDelete={this.handleRenderInputDelete}
                    handleRenderInputProperities_text={
                      this.handleRenderInputProperities_text
                    }
                    handleRenderInputProperities_boolean={
                      this.handleRenderInputProperities_boolean
                    }
                  />
                </div>
              </div>
            </div>
          </div>

          <div className="col-sm-2">
            <div className="card box-shadow-hover pointer">
              <div className="card-body scrollbar" id="style-2">
                <div className="row">
                  <button
                    type="button"
                    className="btn btn-info btn-lg btn-block"
                    onClick={this.handleSaveForm}
                  >
                    Save
                  </button>
                </div>
                <br />
                <div className="row">
                  <button
                    type="button"
                    className="btn btn-secondary btn-lg btn-block"
                  >
                    Reset
                  </button>
                </div>
                <br />
                <div className="row">
                  <label className="text-danger" id="errorFormSave"></label>
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

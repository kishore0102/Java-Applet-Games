import React from "react";
import Textfield from "./FormInputType/Textfield";

const BF_FormRender = ({
  renderInput,
  handleRenderInputSelect,
  handleFormNameChange,
}) => (
  <React.Fragment>
    <div className="formName">
      <div className="list-group list-group-item">
        <div className="row justify-content-center">
          <div className="col-sm-4">
            <label>Form Name&nbsp;:&nbsp;&nbsp;</label>
          </div>

          <div className="col-sm-5">
            <input
              id="formName"
              type="text"
              name="formName"
              onChange={handleFormNameChange}
              defaultValue={renderInput.formDetails.formName}
              required
            />
          </div>
        </div>
      </div>
    </div>
    <br />
    {renderInput.formContents.map((input) => {
      if (input.type === "text") {
        return (
          <Textfield
            key={input.inputId}
            input={input}
            handleRenderInputSelect={handleRenderInputSelect}
          />
        );
      } else {
        return <span></span>;
      }
    })}
  </React.Fragment>
);

export default BF_FormRender;

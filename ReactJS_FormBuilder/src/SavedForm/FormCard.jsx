import React from "react";

const FormCard = ({ oneForm, handleFormChange }) => (
  <React.Fragment>
    {oneForm.formContents.map((input) => {
      if (input.type === "text") {
        return (
          <div
            key={input.inputId}
            id={input.inputId}
            className="list-group-item"
          >
            <div className="row">
              <div className="col-sm-5">
                <label>
                  {input.properities.labelText}
                  &nbsp;:&nbsp;&nbsp;&nbsp;
                </label>
              </div>

              <div className="col-sm-7">
                <input
                  className="form-control"
                  id={input.inputId + "_*_" + input.properities.labelText}
                  type="text"
                  onChange={handleFormChange}
                  name={input.properities.labelText}
                />
                <label className="instruction">
                  #&nbsp;{input.properities.placeholderText}
                </label>
              </div>
            </div>
          </div>
        );
      } else {
        return <span></span>;
      }
    })}
  </React.Fragment>
);

export default FormCard;

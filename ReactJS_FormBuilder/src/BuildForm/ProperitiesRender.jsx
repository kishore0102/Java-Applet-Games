import React from "react";

const BF_ProperitiesRender = ({
  renderInput,
  renderInputSelected,
  handleRenderInputDelete,
  handleRenderInputProperities_text,
  handleRenderInputProperities_boolean,
}) => (
  <React.Fragment>
    {renderInput.formContents.map((input) => {
      if (input.inputId === renderInputSelected) {
        if (input.type === "text") {
          return (
            <div key={input.inputId} className="properities">
              <div className="list-group-item list-group-item-action">
                <div className="row">
                  <button
                    id={input.inputId}
                    type="button"
                    className="btn btn-danger"
                    onClick={handleRenderInputDelete}
                  >
                    Delete this !!!
                  </button>
                </div>
              </div>
              <div className="list-group-item list-group-item-action">
                <div className="row">
                  <label>Label Text</label>
                </div>

                <div className="row">
                  <input
                    className="form-control"
                    id={input.inputId + "_*_labelText"}
                    type="text"
                    name="labelText"
                    defaultValue={input.properities.labelText}
                    onChange={handleRenderInputProperities_text}
                  />
                </div>
              </div>

              <div className="list-group-item list-group-item-action">
                <div className="row">
                  <label>Placeholder Text</label>
                </div>

                <div className="row">
                  <input
                    className="form-control"
                    id={input.inputId + "_*_placeholder"}
                    type="text"
                    name="placeholderText"
                    defaultValue={input.properities.placeholderText}
                    onChange={handleRenderInputProperities_text}
                  />
                </div>
              </div>

              <div className="list-group-item list-group-item-action">
                <div className="row">
                  <label>Minimum Length</label>
                </div>

                <div className="row">
                  <input
                    className="form-control"
                    id={input.inputId + "_*_minimumLength"}
                    type="number"
                    name="minimumLength"
                    min="0"
                    max="255"
                    defaultValue={input.properities.minimumLength}
                    onChange={handleRenderInputProperities_text}
                  />
                </div>
              </div>

              <div className="list-group-item list-group-item-action">
                <div className="row">
                  <label>Maximum Length</label>
                </div>

                <div className="row">
                  <input
                    className="form-control"
                    id={input.inputId + "_*_maximumLength"}
                    type="number"
                    min="0"
                    max="255"
                    name="maximumLength"
                    defaultValue={input.properities.maximumLength}
                    onChange={handleRenderInputProperities_text}
                  />
                </div>
              </div>

              <div className="list-group-item list-group-item-action">
                <div className="row">
                  <label>Required</label>
                </div>

                <div className="row">
                  <div className="form-check">
                    <input
                      className="form-check-input"
                      type="radio"
                      name="required"
                      id={input.inputId + "_*_required_yes"}
                      onChange={handleRenderInputProperities_boolean}
                    />
                    <label
                      className="form-check-label"
                      htmlFor={input.inputId + "_*_required_yes"}
                    >
                      Yes
                    </label>
                  </div>
                  <span>&nbsp;&nbsp;</span>
                  <div className="form-check">
                    <input
                      className="form-check-input"
                      type="radio"
                      name="required"
                      id={input.inputId + "_*_required_no"}
                      onChange={handleRenderInputProperities_boolean}
                    />
                    <label
                      className="form-check-label"
                      htmlFor={input.inputId + "_*_required_no"}
                    >
                      No
                    </label>
                  </div>
                </div>
              </div>
            </div>
          );
        } else {
          return <span></span>;
        }
      } else {
        return <span></span>;
      }
    })}
  </React.Fragment>
);

export default BF_ProperitiesRender;

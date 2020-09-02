defmodule SmzrWeb.ApiAuthPipeline do
  use Guardian.Plug.Pipeline, otp_app: :sample,
                              module: Smzr.Guardian,
                              error_handler: SmzrWeb.ApiAuthErrorHandler

  plug Guardian.Plug.VerifyHeader, realm: "Bearer"
  plug Guardian.Plug.EnsureAuthenticated
  plug Guardian.Plug.LoadResource
end
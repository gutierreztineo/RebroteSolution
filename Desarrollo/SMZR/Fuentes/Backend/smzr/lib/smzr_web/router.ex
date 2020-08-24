defmodule SmzrWeb.Router do
  use SmzrWeb, :router
  alias SmzrWeb.ApiAuthPipeline

  pipeline :browser do
    plug :accepts, ["html"]
    plug :fetch_session
    plug :fetch_live_flash
    plug :put_root_layout, {SmzrWeb.LayoutView, :root}
    plug :protect_from_forgery
    plug :put_secure_browser_headers
  end

  pipeline :api do
    plug :accepts, ["json"]
    plug :fetch_session
  end

  scope "/", SmzrWeb do
    pipe_through :browser

    live "/", PageLive, :index

    #Location
    live "/locations", LocationLive.Index, :index
    live "/locations/new", LocationLive.Index, :new
    live "/locations/:id/edit", LocationLive.Index, :edit

    live "/locations/:id", LocationLive.Show, :show
    live "/locations/:id/show/edit", LocationLive.Show, :edit

    #Users
    live "/users", UserLive.Index, :index
    live "/users/new", UserLive.Index, :new
    live "/users/:id/edit", UserLive.Index, :edit

    live "/users/:id", UserLive.Show, :show
    live "/users/:id/show/edit", UserLive.Show, :edit

    #UserLocation
    live "/user_locations", UserLocationLive.Index, :index
    live "/user_locations/new", UserLocationLive.Index, :new
    live "/user_locations/:id/edit", UserLocationLive.Index, :edit

    live "/user_locations/:id", UserLocationLive.Show, :show
    live "/user_locations/:id/show/edit", UserLocationLive.Show, :edit
    
    #Profile
    live "/profiles", ProfileLive.Index, :index
    live "/profiles/new", ProfileLive.Index, :new
    live "/profiles/:id/edit", ProfileLive.Index, :edit

    live "/profiles/:id", ProfileLive.Show, :show
    live "/profiles/:id/show/edit", ProfileLive.Show, :edit

    #Ailments
    live "/ailments", AilmentLive.Index, :index
    live "/ailments/new", AilmentLive.Index, :new
    live "/ailments/:id/edit", AilmentLive.Index, :edit

    live "/ailments/:id", AilmentLive.Show, :show
    live "/ailments/:id/show/edit", AilmentLive.Show, :edit

    #Profile
    live "/profile_ailments", ProfileAilmentLive.Index, :index
    live "/profile_ailments/new", ProfileAilmentLive.Index, :new
    live "/profile_ailments/:id/edit", ProfileAilmentLive.Index, :edit

    live "/profile_ailments/:id", ProfileAilmentLive.Show, :show
    live "/profile_ailments/:id/show/edit", ProfileAilmentLive.Show, :edit

    #AilmentLevel
    live "/ailment_levels", AilmentLevelLive.Index, :index
    live "/ailment_levels/new", AilmentLevelLive.Index, :new
    live "/ailment_levels/:id/edit", AilmentLevelLive.Index, :edit

    live "/ailment_levels/:id", AilmentLevelLive.Show, :show
    live "/ailment_levels/:id/show/edit", AilmentLevelLive.Show, :edit

    #Risk
    live "/risks", RiskLive.Index, :index
    live "/risks/new", RiskLive.Index, :new
    live "/risks/:id/edit", RiskLive.Index, :edit

    live "/risks/:id", RiskLive.Show, :show
    live "/risks/:id/show/edit", RiskLive.Show, :edit

    #ProfileRisk
    live "/profile_risks", ProfileRiskLive.Index, :index
    live "/profile_risks/new", ProfileRiskLive.Index, :new
    live "/profile_risks/:id/edit", ProfileRiskLive.Index, :edit

    live "/profile_risks/:id", ProfileRiskLive.Show, :show
    live "/profile_risks/:id/show/edit", ProfileRiskLive.Show, :edit

    #Advice
    live "/advices", AdviceLive.Index, :index
    live "/advices/new", AdviceLive.Index, :new
    live "/advices/:id/edit", AdviceLive.Index, :edit

    live "/advices/:id", AdviceLive.Show, :show
    live "/advices/:id/show/edit", AdviceLive.Show, :edit

    #AilmentAdvice
    live "/ailment_advices", AilmentAdviceLive.Index, :index
    live "/ailment_advices/new", AilmentAdviceLive.Index, :new
    live "/ailment_advices/:id/edit", AilmentAdviceLive.Index, :edit

    live "/ailment_advices/:id", AilmentAdviceLive.Show, :show
    live "/ailment_advices/:id/show/edit", AilmentAdviceLive.Show, :edit



  end

  # Other scopes may use custom stacks.
  scope "/api", SmzrWeb do
    pipe_through :api

    post "/sign_up", UserController, :create_jwt
    post "/sign_in", UserController, :sign_in_jwt
    post "/exists", UserController, :exists_user
    get "/test_mail", UserController, :test_mail

  end

  pipeline :jwt_authenticated do
    plug ApiAuthPipeline
  end

  scope "/api", SmzrWeb do
    pipe_through [:api, :jwt_authenticated]

    resources "/locations", LocationController, except: [:new, :edit]
    resources "/users", UserController, except: [:new, :edit]
    #post "/users/sign_in", UserController, :sign_in
    resources "/user_locations", UserLocationController, except: [:new, :edit]
    resources "/profiles", ProfileController, except: [:new, :edit]
    resources "/ailments", AilmentController, except: [:new, :edit]
    resources "/ailment_levels", AilmentLevelController, except: [:new, :edit]
    resources "/profile_ailments", ProfileAilmentController, except: [:new, :edit]
    resources "/risks", RiskController, except: [:new, :edit]
    resources "/profile_risks", ProfileRiskController, except: [:new, :edit]
    resources "/advices", AdviceController, except: [:new, :edit]
    resources "/ailment_advices", AilmentAdviceController, except: [:new, :edit]

    post "/my/profile", ProfileController, :my_profile

  end


  # Enables LiveDashboard only for development
  #
  # If you want to use the LiveDashboard in production, you should put
  # it behind authentication and allow only admins to access it.
  # If your application does not have an admins-only section yet,
  # you can use Plug.BasicAuth to set up some basic authentication
  # as long as you are also using SSL (which you should anyway).

  #if Mix.env() in [:dev, :test, :prod] do
    import Phoenix.LiveDashboard.Router

    scope "/" do
      pipe_through :browser
      live_dashboard "/dashboard", metrics: SmzrWeb.Telemetry
    end
  #end
end

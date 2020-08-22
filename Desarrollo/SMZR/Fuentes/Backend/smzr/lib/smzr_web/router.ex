defmodule SmzrWeb.Router do
  use SmzrWeb, :router

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


  end

  # Other scopes may use custom stacks.
  scope "/api", SmzrWeb do
    pipe_through :api

    resources "/locations", LocationController, except: [:new, :edit]
    resources "/users", UserController, except: [:new, :edit]
    resources "/profiles", ProfileController, except: [:new, :edit]
    resources "/ailments", AilmentController, except: [:new, :edit]
    resources "/profile_ailments", ProfileAilmentController, except: [:new, :edit]

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

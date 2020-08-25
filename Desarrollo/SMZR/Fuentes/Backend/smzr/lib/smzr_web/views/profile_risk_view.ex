defmodule SmzrWeb.ProfileRiskView do
  use SmzrWeb, :view
  alias SmzrWeb.ProfileRiskView

  def render("index.json", %{profile_risks: profile_risks}) do
    %{data: render_many(profile_risks, ProfileRiskView, "profile_risk.json")}
  end

  def render("show.json", %{profile_risk: profile_risk}) do
    %{data: render_one(profile_risk, ProfileRiskView, "profile_risk.json")}
  end

  def render("profile_risk.json", %{profile_risk: profile_risk}) do
    %{id: profile_risk.id}
  end
end

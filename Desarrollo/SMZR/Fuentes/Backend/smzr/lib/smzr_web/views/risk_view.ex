defmodule SmzrWeb.RiskView do
  use SmzrWeb, :view
  alias SmzrWeb.RiskView

  def render("index.json", %{risks: risks}) do
    %{data: render_many(risks, RiskView, "risk.json")}
  end

  def render("show.json", %{risk: risk}) do
    %{data: render_one(risk, RiskView, "risk.json")}
  end

  def render("risk.json", %{risk: risk}) do
    %{id: risk.id,
      descripcion: risk.descripcion,
      name: risk.name}
  end
end

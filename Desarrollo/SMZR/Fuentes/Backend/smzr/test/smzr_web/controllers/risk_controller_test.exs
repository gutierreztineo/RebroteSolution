defmodule SmzrWeb.RiskControllerTest do
  use SmzrWeb.ConnCase

  alias Smzr.Monitoring
  alias Smzr.Monitoring.Risk

  @create_attrs %{
    descripcion: "some descripcion",
    name: "some name"
  }
  @update_attrs %{
    descripcion: "some updated descripcion",
    name: "some updated name"
  }
  @invalid_attrs %{descripcion: nil, name: nil}

  def fixture(:risk) do
    {:ok, risk} = Monitoring.create_risk(@create_attrs)
    risk
  end

  setup %{conn: conn} do
    {:ok, conn: put_req_header(conn, "accept", "application/json")}
  end

  describe "index" do
    test "lists all risks", %{conn: conn} do
      conn = get(conn, Routes.risk_path(conn, :index))
      assert json_response(conn, 200)["data"] == []
    end
  end

  describe "create risk" do
    test "renders risk when data is valid", %{conn: conn} do
      conn = post(conn, Routes.risk_path(conn, :create), risk: @create_attrs)
      assert %{"id" => id} = json_response(conn, 201)["data"]

      conn = get(conn, Routes.risk_path(conn, :show, id))

      assert %{
               "id" => id,
               "descripcion" => "some descripcion",
               "name" => "some name"
             } = json_response(conn, 200)["data"]
    end

    test "renders errors when data is invalid", %{conn: conn} do
      conn = post(conn, Routes.risk_path(conn, :create), risk: @invalid_attrs)
      assert json_response(conn, 422)["errors"] != %{}
    end
  end

  describe "update risk" do
    setup [:create_risk]

    test "renders risk when data is valid", %{conn: conn, risk: %Risk{id: id} = risk} do
      conn = put(conn, Routes.risk_path(conn, :update, risk), risk: @update_attrs)
      assert %{"id" => ^id} = json_response(conn, 200)["data"]

      conn = get(conn, Routes.risk_path(conn, :show, id))

      assert %{
               "id" => id,
               "descripcion" => "some updated descripcion",
               "name" => "some updated name"
             } = json_response(conn, 200)["data"]
    end

    test "renders errors when data is invalid", %{conn: conn, risk: risk} do
      conn = put(conn, Routes.risk_path(conn, :update, risk), risk: @invalid_attrs)
      assert json_response(conn, 422)["errors"] != %{}
    end
  end

  describe "delete risk" do
    setup [:create_risk]

    test "deletes chosen risk", %{conn: conn, risk: risk} do
      conn = delete(conn, Routes.risk_path(conn, :delete, risk))
      assert response(conn, 204)

      assert_error_sent 404, fn ->
        get(conn, Routes.risk_path(conn, :show, risk))
      end
    end
  end

  defp create_risk(_) do
    risk = fixture(:risk)
    %{risk: risk}
  end
end

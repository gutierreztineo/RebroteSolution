defmodule SmzrWeb.ProfileRiskControllerTest do
  use SmzrWeb.ConnCase

  alias Smzr.Monitoring
  alias Smzr.Monitoring.ProfileRisk

  @create_attrs %{

  }
  @update_attrs %{

  }
  @invalid_attrs %{}

  def fixture(:profile_risk) do
    {:ok, profile_risk} = Monitoring.create_profile_risk(@create_attrs)
    profile_risk
  end

  setup %{conn: conn} do
    {:ok, conn: put_req_header(conn, "accept", "application/json")}
  end

  describe "index" do
    test "lists all profile_risks", %{conn: conn} do
      conn = get(conn, Routes.profile_risk_path(conn, :index))
      assert json_response(conn, 200)["data"] == []
    end
  end

  describe "create profile_risk" do
    test "renders profile_risk when data is valid", %{conn: conn} do
      conn = post(conn, Routes.profile_risk_path(conn, :create), profile_risk: @create_attrs)
      assert %{"id" => id} = json_response(conn, 201)["data"]

      conn = get(conn, Routes.profile_risk_path(conn, :show, id))

      assert %{
               "id" => id
             } = json_response(conn, 200)["data"]
    end

    test "renders errors when data is invalid", %{conn: conn} do
      conn = post(conn, Routes.profile_risk_path(conn, :create), profile_risk: @invalid_attrs)
      assert json_response(conn, 422)["errors"] != %{}
    end
  end

  describe "update profile_risk" do
    setup [:create_profile_risk]

    test "renders profile_risk when data is valid", %{conn: conn, profile_risk: %ProfileRisk{id: id} = profile_risk} do
      conn = put(conn, Routes.profile_risk_path(conn, :update, profile_risk), profile_risk: @update_attrs)
      assert %{"id" => ^id} = json_response(conn, 200)["data"]

      conn = get(conn, Routes.profile_risk_path(conn, :show, id))

      assert %{
               "id" => id
             } = json_response(conn, 200)["data"]
    end

    test "renders errors when data is invalid", %{conn: conn, profile_risk: profile_risk} do
      conn = put(conn, Routes.profile_risk_path(conn, :update, profile_risk), profile_risk: @invalid_attrs)
      assert json_response(conn, 422)["errors"] != %{}
    end
  end

  describe "delete profile_risk" do
    setup [:create_profile_risk]

    test "deletes chosen profile_risk", %{conn: conn, profile_risk: profile_risk} do
      conn = delete(conn, Routes.profile_risk_path(conn, :delete, profile_risk))
      assert response(conn, 204)

      assert_error_sent 404, fn ->
        get(conn, Routes.profile_risk_path(conn, :show, profile_risk))
      end
    end
  end

  defp create_profile_risk(_) do
    profile_risk = fixture(:profile_risk)
    %{profile_risk: profile_risk}
  end
end

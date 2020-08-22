defmodule SmzrWeb.ProfileAilmentControllerTest do
  use SmzrWeb.ConnCase

  alias Smzr.Monitoring
  alias Smzr.Monitoring.ProfileAilment

  @create_attrs %{

  }
  @update_attrs %{

  }
  @invalid_attrs %{}

  def fixture(:profile_ailment) do
    {:ok, profile_ailment} = Monitoring.create_profile_ailment(@create_attrs)
    profile_ailment
  end

  setup %{conn: conn} do
    {:ok, conn: put_req_header(conn, "accept", "application/json")}
  end

  describe "index" do
    test "lists all profile_ailments", %{conn: conn} do
      conn = get(conn, Routes.profile_ailment_path(conn, :index))
      assert json_response(conn, 200)["data"] == []
    end
  end

  describe "create profile_ailment" do
    test "renders profile_ailment when data is valid", %{conn: conn} do
      conn = post(conn, Routes.profile_ailment_path(conn, :create), profile_ailment: @create_attrs)
      assert %{"id" => id} = json_response(conn, 201)["data"]

      conn = get(conn, Routes.profile_ailment_path(conn, :show, id))

      assert %{
               "id" => id
             } = json_response(conn, 200)["data"]
    end

    test "renders errors when data is invalid", %{conn: conn} do
      conn = post(conn, Routes.profile_ailment_path(conn, :create), profile_ailment: @invalid_attrs)
      assert json_response(conn, 422)["errors"] != %{}
    end
  end

  describe "update profile_ailment" do
    setup [:create_profile_ailment]

    test "renders profile_ailment when data is valid", %{conn: conn, profile_ailment: %ProfileAilment{id: id} = profile_ailment} do
      conn = put(conn, Routes.profile_ailment_path(conn, :update, profile_ailment), profile_ailment: @update_attrs)
      assert %{"id" => ^id} = json_response(conn, 200)["data"]

      conn = get(conn, Routes.profile_ailment_path(conn, :show, id))

      assert %{
               "id" => id
             } = json_response(conn, 200)["data"]
    end

    test "renders errors when data is invalid", %{conn: conn, profile_ailment: profile_ailment} do
      conn = put(conn, Routes.profile_ailment_path(conn, :update, profile_ailment), profile_ailment: @invalid_attrs)
      assert json_response(conn, 422)["errors"] != %{}
    end
  end

  describe "delete profile_ailment" do
    setup [:create_profile_ailment]

    test "deletes chosen profile_ailment", %{conn: conn, profile_ailment: profile_ailment} do
      conn = delete(conn, Routes.profile_ailment_path(conn, :delete, profile_ailment))
      assert response(conn, 204)

      assert_error_sent 404, fn ->
        get(conn, Routes.profile_ailment_path(conn, :show, profile_ailment))
      end
    end
  end

  defp create_profile_ailment(_) do
    profile_ailment = fixture(:profile_ailment)
    %{profile_ailment: profile_ailment}
  end
end

defmodule SmzrWeb.AilmentAdviceLiveTest do
  use SmzrWeb.ConnCase

  import Phoenix.LiveViewTest

  alias Smzr.Monitoring

  @create_attrs %{}
  @update_attrs %{}
  @invalid_attrs %{}

  defp fixture(:ailment_advice) do
    {:ok, ailment_advice} = Monitoring.create_ailment_advice(@create_attrs)
    ailment_advice
  end

  defp create_ailment_advice(_) do
    ailment_advice = fixture(:ailment_advice)
    %{ailment_advice: ailment_advice}
  end

  describe "Index" do
    setup [:create_ailment_advice]

    test "lists all ailment_advices", %{conn: conn, ailment_advice: ailment_advice} do
      {:ok, _index_live, html} = live(conn, Routes.ailment_advice_index_path(conn, :index))

      assert html =~ "Listing Ailment advices"
    end

    test "saves new ailment_advice", %{conn: conn} do
      {:ok, index_live, _html} = live(conn, Routes.ailment_advice_index_path(conn, :index))

      assert index_live |> element("a", "New Ailment advice") |> render_click() =~
               "New Ailment advice"

      assert_patch(index_live, Routes.ailment_advice_index_path(conn, :new))

      assert index_live
             |> form("#ailment_advice-form", ailment_advice: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        index_live
        |> form("#ailment_advice-form", ailment_advice: @create_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.ailment_advice_index_path(conn, :index))

      assert html =~ "Ailment advice created successfully"
    end

    test "updates ailment_advice in listing", %{conn: conn, ailment_advice: ailment_advice} do
      {:ok, index_live, _html} = live(conn, Routes.ailment_advice_index_path(conn, :index))

      assert index_live |> element("#ailment_advice-#{ailment_advice.id} a", "Edit") |> render_click() =~
               "Edit Ailment advice"

      assert_patch(index_live, Routes.ailment_advice_index_path(conn, :edit, ailment_advice))

      assert index_live
             |> form("#ailment_advice-form", ailment_advice: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        index_live
        |> form("#ailment_advice-form", ailment_advice: @update_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.ailment_advice_index_path(conn, :index))

      assert html =~ "Ailment advice updated successfully"
    end

    test "deletes ailment_advice in listing", %{conn: conn, ailment_advice: ailment_advice} do
      {:ok, index_live, _html} = live(conn, Routes.ailment_advice_index_path(conn, :index))

      assert index_live |> element("#ailment_advice-#{ailment_advice.id} a", "Delete") |> render_click()
      refute has_element?(index_live, "#ailment_advice-#{ailment_advice.id}")
    end
  end

  describe "Show" do
    setup [:create_ailment_advice]

    test "displays ailment_advice", %{conn: conn, ailment_advice: ailment_advice} do
      {:ok, _show_live, html} = live(conn, Routes.ailment_advice_show_path(conn, :show, ailment_advice))

      assert html =~ "Show Ailment advice"
    end

    test "updates ailment_advice within modal", %{conn: conn, ailment_advice: ailment_advice} do
      {:ok, show_live, _html} = live(conn, Routes.ailment_advice_show_path(conn, :show, ailment_advice))

      assert show_live |> element("a", "Edit") |> render_click() =~
               "Edit Ailment advice"

      assert_patch(show_live, Routes.ailment_advice_show_path(conn, :edit, ailment_advice))

      assert show_live
             |> form("#ailment_advice-form", ailment_advice: @invalid_attrs)
             |> render_change() =~ "can&apos;t be blank"

      {:ok, _, html} =
        show_live
        |> form("#ailment_advice-form", ailment_advice: @update_attrs)
        |> render_submit()
        |> follow_redirect(conn, Routes.ailment_advice_show_path(conn, :show, ailment_advice))

      assert html =~ "Ailment advice updated successfully"
    end
  end
end
